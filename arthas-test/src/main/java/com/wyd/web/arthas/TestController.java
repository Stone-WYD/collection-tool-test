package com.wyd.web.arthas;

import com.alibaba.excel.EasyExcel;
import com.google.gson.Gson;
import com.wyd.web.common.AjaxResult;
import com.wyd.web.common.Query;
import com.wyd.web.model.zm.IREQUEST;
import com.wyd.web.model.zm.ZmCommonResult;
import com.wyd.web.model.zm.user.ETEMPLBANK;
import com.wyd.web.model.zm.user.ETEMPLBASIC;
import com.wyd.web.model.zm.user.ETEMPLPOST;
import com.wyd.web.model.zm.user.UserInfoBody;
import lombok.Data;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Stone
 * @date 2024-12-26
 * @Description: 模拟测试因高并发而引起的内存溢出问题
 */
@RestController
@RequestMapping("/arthas/leak")
public class TestController {

    /**
     * 大量数据 + 处理慢
     */
    @GetMapping("/test")
    public void test1() throws InterruptedException {
        byte[] bytes = new byte[1024 * 1024 * 100];//100m
        Thread.sleep(10 * 1000L);
    }

    private final Map<Long, UserEntity> userCache = new HashMap<>();

    /**
     * 登录接口 传递名字和id,放入hashmap中
     */
    @PostMapping("/login")
    public void login(String name,Long id){
        userCache.put(id,new UserEntity(id,name));
    }

    @PostMapping("/feign")
    public AjaxResult<UserEntity> feignTest(String name, Long id){
        UserEntity data = new UserEntity(id, name);
        System.out.println("====================== feign 被调用了 ===========================");
        return AjaxResult.getTrueAjaxResult(data);
    }

    @PostMapping("/feign2")
    public AjaxResult<UserEntity> feignTest2(@RequestBody Query<UserEntity> query){
        UserEntity userEntity = query.getQuery();
        userEntity.setId(userEntity.getId() + 1);
        userEntity.setName(userEntity.getName() + "+ test");
        System.out.println("====================== feign2 被调用了 ===========================");
        return AjaxResult.getTrueAjaxResult(userEntity);
    }

    @PostMapping("/feign3")
    public AjaxResult<UserEntity> feignTest3(@RequestBody UserEntity userEntity){
        userEntity.setId(userEntity.getId() + 1);
        userEntity.setName(userEntity.getName() + "+ test");
        System.out.println("====================== feign3 被调用了 ===========================");
        return AjaxResult.getTrueAjaxResult(userEntity);
    }

    @PostMapping("/feign4")
    public String feignTest4(){
        UserInfoBody userInfoBody = new UserInfoBody();
        List<ETEMPLBASIC> list = new ArrayList<>();
        ETEMPLBASIC basic = new ETEMPLBASIC();
        basic.setZATTR1("123");
        basic.setZATTR2("222");
        list.add(basic);
        userInfoBody.setETEMPLBASIC(list);

        IREQUEST<UserInfoBody> irequest = new IREQUEST<>();
        ZmCommonResult<UserInfoBody> result = new ZmCommonResult<>();
        irequest.setBODY(userInfoBody);
        result.setIREQUEST(irequest);
        System.out.println("====================== feign4 被调用了 ===========================");
        return new Gson().toJson(result);
    }

    @PostMapping("/feign5")
    public ZmCommonResult<UserInfoBody>  feignTest5(){
        UserInfoBody userInfoBody = new UserInfoBody();
        List<ETEMPLBASIC> list = new ArrayList<>();
        ETEMPLBASIC basic = new ETEMPLBASIC();
        basic.setZATTR1("123");
        basic.setZATTR2("222");
        list.add(basic);
        userInfoBody.setETEMPLBASIC(list);

        IREQUEST<UserInfoBody> irequest = new IREQUEST<>();
        ZmCommonResult<UserInfoBody> result = new ZmCommonResult<>();
        irequest.setBODY(userInfoBody);
        result.setIREQUEST(irequest);
        System.out.println("====================== feign4 被调用了 ===========================");
        return result;
    }

    @Data
    public static class UserEntity {
        public UserEntity() {
        }

        public UserEntity(Long id, String name) {
            this.id = id;
            this.name = name;
        }

        private Long id;
        private String name;
    }

    @ResponseBody
    @RequestMapping("/printRecordPatrolMsgs")
    public ResponseEntity<byte[]> printRecordPatrolMsgs() {
        UserEntity userEntity = new UserEntity();
        userEntity.setName("test");
        userEntity.setId(1L);
        List<UserEntity> list = new ArrayList<>();
        list.add(userEntity);

        try(ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            EasyExcel.write(outputStream, UserEntity.class)
                    .sheet("Sheet1")
                    .doWrite(list); // list 是你要导出的数据集合

            return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment; filename=export.xlsx")
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(outputStream.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /*public static void main(String[] args) {
        Query<UserEntity> query = new Query<>();
        query.setPageNum(1);
        query.setPageSize(1);
        UserEntity userEntity = new UserEntity(1L, "wyd");
        query.setQuery(userEntity);
        Gson gson = new Gson();
        System.out.println(gson.toJson(userEntity));
        System.out.println(gson.toJson(query));
    }*/

    public static void main(String[] args) {
        UserInfoBody userInfoBody = new UserInfoBody();
        List<ETEMPLBASIC> list = new ArrayList<>();
        ETEMPLBASIC basic = new ETEMPLBASIC();
        basic.setZATTR1("123");
        basic.setZATTR2("222");
        list.add(basic);
        userInfoBody.setETEMPLBASIC(list);

        IREQUEST<UserInfoBody> irequest = new IREQUEST<>();
        ZmCommonResult<UserInfoBody> result = new ZmCommonResult<>();
        irequest.setBODY(userInfoBody);
        result.setIREQUEST(irequest);
        System.out.println("====================== feign4 被调用了 ===========================");
        System.out.println(new Gson().toJson(result));
    }

}
