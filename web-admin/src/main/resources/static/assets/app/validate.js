/**
 * 函数对象
 * @constructor
 */
var Validate = function () {
    var initValidate = function () {
        $.validator.addMethod("mobile", function (value, element) {
            var length = value.length;
            var mobile = /^1([38][0-9]|4[579]|5[0-3,5-9]|6[6]|7[0135678]|9[89])\d{8}$/;
            return this.optional(element) || (length == 11 && mobile.test(value));
        }, "手机号码格式错误");
    };
    /**
     * 表单验证
     * @param formId
     */
    var ValidateForm = function (formId) {
        $("#" + formId).validate({
            errorElement: 'span',
            errorClass: 'help-block',
            errorPlacement: function (error, element) {
                element.parent().parent().attr("class", "form-group has-error");
                error.insertAfter(element);
            }
        });
    };
    return {
        initValidate: function () {
            initValidate();
        },
        validateForm: function (formId) {
            ValidateForm(formId);
        }
    }
}();

$(function () {
    Validate.initValidate();
});
