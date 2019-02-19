package generator;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.internal.DefaultCommentGenerator;

/**
 * @author LiaoWei
 */
public class CommentGenerator extends DefaultCommentGenerator {
    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        String format = "/** %s */";
        String comment = String.format(format, introspectedColumn.getRemarks());
        field.addJavaDocLine(comment);
    }

    @Override
    public void addGeneralMethodComment(Method method, IntrospectedTable introspectedTable) {
        method.addJavaDocLine("/**");
        method.addJavaDocLine("* This method corresponds to the database table detail");
        method.addJavaDocLine("*/");
    }
}
