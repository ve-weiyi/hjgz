package com.ve.generator;


import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.FileType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @Author zk
 * @Description 演示例子，执行 main 方法控制台输入模块表名回车自动生成对应项目目录中
 * @Date create in 2021-05-12 09:20
 * @Modified by:https://blog.csdn.net/u011055858/article/details/117125900
 */
public class CodeGenerator {
    private static String moduleName = "module-generator";
    private static String projectPath = System.getProperty("user.dir")+ "/"+moduleName;
    private static String DriverName ="com.mysql.cj.jdbc.Driver";
    private static String Url = "jdbc:mysql://124.221.119.149:3306/discuz?characterEncoding=utf-8&serverTimezone=GMT%2B8&useSSL=false";
    private static String username = "root";
    private static String password = "ZnouGrp5sAql5PNj";
    private static String tablePrefix="pre_";

    /**
     * t_privacy_type,t_privacy_folder,t_privacy_tag,t_user_privacy_tag,t_privacy_details_pass,t_privacy_details_card,t_user_privacy_pass,t_user_privacy_card
     */


    /**
     * 读取控制台内容
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotBlank(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    //全局配置
    public static GlobalConfig getGlobalConfig(){
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(projectPath + "/src/main/java");
        //作者
        gc.setAuthor("weiyi");
        //打开输出目录
        gc.setOpen(false);

        // 实体属性 Swagger2 注解
        gc.setSwagger2(true);
        //开启 BaseResultMap通用查询映射结果 BaseResultMap
        gc.setBaseResultMap(true);
        //开启 baseColumnList通用查询结果列 Base_Column_List
        gc.setBaseColumnList(true);

        //如果存在文件是否覆盖
        gc.setFileOverride(true);
        //设置主键策略为雪花算法
        gc.setIdType(IdType.ASSIGN_ID);

        //自定义文件命名，注意 %s 会自动填充表实体属性！
        gc.setMapperName("%sMapper");
        gc.setXmlName("%sMapper");
        //I%sService去掉service前面的I前缀
        gc.setServiceName("I%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setControllerName("%sController");

        return gc;
    }

    //配置数据源
    public static DataSourceConfig getDataSourceConfig(){
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDriverName(DriverName);
        dsc.setUrl(Url);
        dsc.setUsername(username);
        dsc.setPassword(password);
        dsc.setDbType(DbType.MYSQL);
        return dsc;
    }

    //包配置
    public static PackageConfig getPackageConfig(){
        PackageConfig pc = new PackageConfig();
        //将来代码会生成于com.kaihang的目录下的jmsg模块

        //自定义输入模块名称
        //pc.setModuleName(moduleName);//模块名
        pc.setParent("com.ve.locker")
                .setEntity("entity")
                .setMapper("dao")
                .setService("service")
                .setServiceImpl("service.impl")
                .setController("controller");
        return pc;
    }

    //策略配置
    public static StrategyConfig getStrategyConfig(){
        StrategyConfig strategy = new StrategyConfig();
        //下划线转驼峰命名
        strategy.setNaming(NamingStrategy.underline_to_camel);
        //列名下划线转驼峰命名
        strategy.setColumnNaming(NamingStrategy.no_change);
        //strategy.setSuperEntityClass("你自己的父类实体,没有就不用设置!");
        //自动支持Lombok注解
        strategy.setEntityLombokModel(true);
        //生成 @RestController 控制器
        strategy.setRestControllerStyle(true);
        //设置下划线命名 localhost:8080/hello_id_2
        strategy.setControllerMappingHyphenStyle(true);
        //设置实体类逻辑删除字段
        //strategy.setLogicDeleteFieldName("deleted");
        //设置自动填充字段
        TableFill createTime = new TableFill("createTime", FieldFill.INSERT);
        TableFill updateTime = new TableFill("updateTime", FieldFill.INSERT_UPDATE);
        TableFill create_time = new TableFill("create_time", FieldFill.INSERT);
        TableFill update_time = new TableFill("update_time", FieldFill.INSERT_UPDATE);

        List<TableFill> list = new ArrayList<>();
        list.add(createTime);
        list.add(updateTime);
        list.add(create_time);
        list.add(update_time);
        strategy.setTableFillList(list);
        //设置乐观锁
        //strategy.setVersionFieldName("version");
        // 公共父类
        //strategy.setSuperControllerClass("你自己的父类控制器,没有就不用设置!");
        // 写于父类中的公共字段
        //strategy.setSuperEntityColumns("id");
        //strategy.setInclude("user","product"); 设置只映射生成两张表：user表和product表
        //配置生成的表由控制台输入
        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
        //表前缀
        strategy.setTablePrefix(tablePrefix);
        return strategy;
    }

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        // 1.全局配置
        GlobalConfig gc = CodeGenerator.getGlobalConfig();
        mpg.setGlobalConfig(gc);
        // 2.数据源配置
        DataSourceConfig dsc = CodeGenerator.getDataSourceConfig();
        mpg.setDataSource(dsc);
        // 3.包配置
        PackageConfig pc = CodeGenerator.getPackageConfig();
        mpg.setPackageInfo(pc);
        // 4.策略配置
        StrategyConfig strategy = CodeGenerator.getStrategyConfig();
        mpg.setStrategy(strategy);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        // 如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
        // String templatePath = "/templates/mapper.xml.vm";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/resources/mapper/"
                        + tableInfo.getEntityName() + "Mapper"
                        + StringPool.DOT_XML;
            }
        });

//        cfg.setFileCreate(new IFileCreate() {
//            /**
//             * 是否需要生成文件
//             */
//            @Override
//            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
//                // 判断自定义文件夹是否需要创建
//                checkDir("调用默认方法创建的目录，自定义目录用");
////                if (fileType == FileType.MAPPER) {
////                    // 已经生成 mapper 文件判断存在，不想重新生成返回 false
////                    return !new File(filePath).exists();
////                }
//                // 允许生成模板文件
//                return true;
//            }
//        });

        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板,不在mapper中新建xml目录并生成xml文件
        TemplateConfig templateConfig = new TemplateConfig();
        //控制不生成controller,空字符串就行
        //templateConfig.setController("");
        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());

        mpg.execute();
    }

}
