package generator;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.internal.DefaultCommentGenerator;

import java.util.List;

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
        String methodName = method.getName();
        String tableName = introspectedTable.getFullyQualifiedTable().getIntrospectedTableName();
        List<Parameter> parameters = method.getParameters();

        method.addJavaDocLine("/**");
        method.addJavaDocLine("* " + methodName);
        for (Parameter parameter : parameters) {
            if ("insert".equals(methodName) || "insertSelective".equals(methodName)
                    || "updateByPrimaryKey".equals(methodName) || "updateByPrimaryKeySelective".equals(methodName)) {
                String format = "* @param %s table %s data object";
                method.addJavaDocLine(String.format(format, parameter.getName(), tableName));
                method.addJavaDocLine("* @return affected rows");
            } else if ("selectByPrimaryKey".equals(methodName)) {
                String type = parameter.getType().getShortNameWithoutTypeArguments();
                String format = "* @param %s primary key";
                method.addJavaDocLine(String.format(format, parameter.getName()));
                format = "* @return table %s data object";
                method.addJavaDocLine(String.format(format, tableName));
            }
        }
        method.addJavaDocLine("*/");
    }
}