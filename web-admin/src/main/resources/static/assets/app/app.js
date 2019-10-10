var App = function () {
    var icheck_master, _icheck, idArr;
    var maxFiles = 1;
    var defaultDropzone = {
        url: "/upload", // 文件提交地址
        method: "post",  // 也可用put
        paramName: "file", // 默认为file
        maxFiles: maxFiles,// 一次性上传的文件数量上限
        maxFilesize: 2, // 文件大小，单位：MB
        acceptedFiles: ".jpg,.gif,.png,.jpeg", // 上传的类型
        addRemoveLinks: true,
        parallelUploads: 1,// 一次上传的文件数量
        dictDefaultMessage: '拖动文件至此或者点击上传',
        dictMaxFilesExceeded: "您最多只能上传 "+maxFiles+" 个文件！",
        dictResponseError: '文件上传失败!',
        dictInvalidFileType: "文件类型只能是*.jpg,*.gif,*.png,*.jpeg。",
        dictFallbackMessage: "浏览器不受支持",
        dictFileTooBig: "文件超过上传文件最大支持.",
        dictRemoveFile: "删除"
    };
    /**
     * 初始化
     */
    var initICheck = function () {
        $('input[type="checkbox"].minimal, input[type="radio"].minimal').iCheck({
            checkboxClass: 'icheckbox_minimal-blue',
            radioClass: 'iradio_minimal-blue'
        });
        // 获取主要的 checkbox
        icheck_master = $('input[type="checkbox"].minimal.icheck_master');
        // 获取所有的checkbox
        _icheck = $('input[type="checkbox"].minimal');
    };
    /**
     * 全选
     */
    var iCheckAll = function () {
        icheck_master.on("ifClicked", function (e) {
            //选中为false
            if (e.target.checked) {
                _icheck.iCheck("uncheck");
            } else {
                _icheck.iCheck("check");
            }
        });
    };

    /**
     * 删除单笔记录
     * @param url 删除链接
     * @param id 需要删除数据的 ID
     */
    var delSingle = function (url, id, msg) {
        // 可选参数
        if (!msg) msg = null;

        // 将 ID 放入数组中，以便和批量删除通用
        idArr = new Array();
        idArr.push(id);

        $("#modal-default-message").html(msg == null ? "您确定删除数据项吗？" : msg);
        $("#modal-default").modal("show");
        /*绑定点击事件 绑定前先取消绑定事件*/
        $("#modal-default-confirm").unbind("click");
        $("#modal-default-confirm").bind("click", function () {
            if (idArr.length !== 0) {
                delData(url);
            }
            $("#modal-default").modal("hide");
        });
    };

    /**
     *批量删除
     * @param url  路径
     */
    var delMul = function (url) {
        idArr = new Array();
        _icheck.each(function () {
            var _id = $(this).attr("id");
            if (_id != null && _id != "undefine" && $(this).is(":checked")) {
                idArr.push(_id);
            }
        });
        if (idArr.length === 0) {
            $("#modal-default-message").html("至少需要选中一个");
        } else {
            $("#modal-default-message").html("确定删除吗");
        }
        $("#modal-default").modal("show");
        /*绑定点击事件 绑定前先取消绑定事件*/
        $("#modal-default-confirm").unbind("click");
        $("#modal-default-confirm").bind("click", function () {
            if (idArr.length !== 0) {
                delData(url);
            }
            $("#modal-default").modal("hide");
        });

    };
    /**
     * AJAX 异步删除
     * @param url
     */
    function delData(url) {
        $.ajax({
            url: url,
            type: "POST",
            data: {"ids": idArr.toString()},
            datatype: "JSON",
            success: function (data) {
                $("#modal_result_confirm").unbind("click");
                if (data.status === 200) {
                    $("#modal-result-message").html("删除成功");
                    $("#modal_result_confirm").bind("click",function () {
                        window.location.reload();
                    });
                } else {
                    $("#modal-result-message").html(data.message);
                }
                $("#modal-result").modal("show");
            }
        });
    }
    /**
     * 初始化dataTables
     */
    var initDataTables = function (url, columns) {
        var _dataTables = $("#datatable").DataTable({
            // 是否开启本地分页
            "paging": true,
            // 是否开启本地排序
            "ordering": false,
            // 是否显示处理状态(排序的时候，数据很多耗费时间长的话，也会显示这个)
            "processing": true,
            // 是否允许 DataTables 开启本地搜索
            "searching": false,
            // 控制 DataTables 的延迟渲染，可以提高初始化的速度
            "deferRender": true,
            // 分页按钮显示选项
            "pagingType": "full_numbers",
            // 是否开启服务器模式
            "serverSide": true,
            ajax: {
                url: url,
                type: "get"
            },
            "columns": columns,
            "drawCallback": function (settings) {
                initICheck();
                iCheckAll();
            },
            "language": {
                "sProcessing": "处理中...",
                "sLengthMenu": "显示 _MENU_ 项结果",
                "sZeroRecords": "没有匹配结果",
                "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
                "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
                "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
                "sInfoPostFix": "",
                "sSearch": "搜索:",
                "sUrl": "",
                "sEmptyTable": "表中数据为空",
                "sLoadingRecords": "载入中...",
                "sInfoThousands": ",",
                "oPaginate": {
                    "sFirst": "首页",
                    "sPrevious": "上页",
                    "sNext": "下页",
                    "sLast": "末页"
                },
                "oAria": {
                    "sSortAscending": ": 以升序排列此列",
                    "sSortDescending": ": 以降序排列此列"
                }
            }
        });
        return _dataTables;
    };
    /**
     * 显示详情模板页面(请求url返回一个页面)
     */
    var showDetail = function (url) {
        $.ajax({
            url: url,
            type: "get",
            dataType: "html",
            success: function (data) {
                $("#modal-result-title").text("查询详情");
                $("#modal-result-message").html(data);
                $("#modal-result").modal("show");
            }
        });
    };

    var initZTree = function (url,autoParam,callback) {
        var setting = {
            view: {
                // 禁止多选
                selectedMulti: false
            },
            async: {
                // 开启异步加载功能
                enable: true,
                // 远程访问地址
                url: url,
                // 选择父节点时会自动将节点中的参数传回服务器再重新取结果
                autoParam: autoParam
            }
        };

        // 初始化 zTree 控件
        $.fn.zTree.init($("#myTree"), setting);
        // 绑定事件
        $("#modal_tree_confirm").bind("click", function () {
            // 获取 zTree 控件
            var zTree = $.fn.zTree.getZTreeObj("myTree");
            // 获取已选中的节点
            var nodes = zTree.getSelectedNodes();
            if (nodes.length == 0) {
                alert("请先选择一个父节点");
            } else {
                //调用回调函数，将节点的值赋予Input
                callback(nodes);
                $("#modal-tree").modal("hide");
            }
        });
    };

    var initDropzone = function (opts) {
        //关闭Dropzone自动发现
        Dropzone.autoDiscover = false;
        $.extend(defaultDropzone,opts);
        $("#"+defaultDropzone.id).dropzone(defaultDropzone);
    };
    return {
        init: function () {
            initICheck();
            iCheckAll();
        },
        delMul: function (url) {
            delMul(url);
        },
        initDataTables: function (url, columns) {
            return initDataTables(url, columns);
        },
        showDetail: function (url) {
            showDetail(url);
        },
        initZTree: function (url,autoParam,callback) {
            initZTree(url,autoParam,callback);
        },
        initDropzone: function (opts) {
            initDropzone(opts);
        },
        delSingle: function (url,id,msg) {
            console.log("url:"+url+",id:"+id);
            delSingle(url,id,msg);
        }
    }
}();
$(function () {
    App.init();
})
