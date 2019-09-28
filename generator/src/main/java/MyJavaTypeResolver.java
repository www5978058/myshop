import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.internal.types.JavaTypeResolverDefaultImpl;

import java.sql.Types;

/**
 * @author wzh
 * @date 2019/9/22 - 11:36
 */
public class MyJavaTypeResolver extends JavaTypeResolverDefaultImpl  {
    public MyJavaTypeResolver() {
        super();
        super.typeMap.put(-6, new JavaTypeResolverDefaultImpl.JdbcTypeInformation("TINYINT", new FullyQualifiedJavaType(Integer.class.getName())));
        typeMap.put(Types.SMALLINT, new JavaTypeResolverDefaultImpl.JdbcTypeInformation("SMALLINT",
                new FullyQualifiedJavaType(Integer.class.getName())));

    }
}
