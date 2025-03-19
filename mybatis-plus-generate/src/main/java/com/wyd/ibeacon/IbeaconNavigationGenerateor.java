package com.wyd.ibeacon;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Arrays;

/**
 * @author xh
 * @date 2025-02-24
 * @Description:
 */
public class IbeaconNavigationGenerateor {

    // TODO 修改服务名以及数据表名
    private static final String DATA_BASE_NAME = "zm_hk_middleware";
    private static final String DATA_SOURCE_USER_NAME  = "root";
    private static final String DATA_SOURCE_PASSWORD  = "123456";
    private static final String[] TABLE_NAMES = new String[]{
            "et_empl_bank",
            "et_empl_basic",
            "et_empl_post",
            "it_org_basic",
            "it_post_basic",
    };
    // 包目录
    private static final String PACKAGE_PARENT_DIR = "com.wyd.zmhkmiddleware.business";
    // 实体类目录，建议将最终目录名设置为 po
    private static String ENTITY_DIR = "model.local.po";
    // service 实现类目录
    private static final String SERVICE_IMPL_DIR = "service.local.impl";
    // mapper xml文件和接口目录
    private static final String MAPPER_DIR = "mapper";

    // TODO 默认生成entity，需要生成DTO修改此变量
    // 一般情况下要先生成 DTO类 然后修改此参数再生成 PO 类。
    private static final Boolean IS_DTO = false;

    public static void main(String[] args) {
        if (IS_DTO) {
            // 将 ENTITY_DIR 最后一个"."后面内容去掉，换成 dto
            ENTITY_DIR = ENTITY_DIR.substring(0, ENTITY_DIR.lastIndexOf(".") + 1) + "dto" ;
        }
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        // 选择 freemarker 引擎，默认 Velocity
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setFileOverride(true);
        // 输出到项目所在目录，便于复制
        gc.setOutputDir(System.getProperty("user.dir"));
        gc.setAuthor("wyd");
        gc.setOpen(false);
        gc.setSwagger2(false);
        gc.setServiceName("%sService");
        gc.setBaseResultMap(true);
        gc.setBaseColumnList(true);

        if (IS_DTO) {
            gc.setSwagger2(true);
            gc.setEntityName("%sDTO");
        }
        mpg.setGlobalConfig(gc);

        // 数据库配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setUrl("jdbc:mysql://localhost:3306/" + DATA_BASE_NAME
                + "?serverTimezone=UTC&useUnicode=true&useSSL=false&characterEncoding=utf8");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername(DATA_SOURCE_USER_NAME);
        dsc.setPassword(DATA_SOURCE_PASSWORD);
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent(PACKAGE_PARENT_DIR);
        pc.setServiceImpl(SERVICE_IMPL_DIR);
        pc.setXml(MAPPER_DIR);
        pc.setEntity(ENTITY_DIR);
        mpg.setPackageInfo(pc);


        // 设置模板
        TemplateConfig tc = new TemplateConfig();
        mpg.setTemplate(tc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setInclude(TABLE_NAMES);
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(pc.getModuleName() + "_");
        // Boolean类型字段是否移除is前缀处理
        strategy.setEntityBooleanColumnRemoveIsPrefix(true);
        strategy.setRestControllerStyle(true);

        // 自动填充字段配置
        strategy.setTableFillList(Arrays.asList(
                new TableFill("create_date", FieldFill.INSERT),
                new TableFill("change_date", FieldFill.INSERT_UPDATE),
                new TableFill("modify_date", FieldFill.UPDATE)
        ));
        mpg.setStrategy(strategy);

        mpg.execute();
    }

}
