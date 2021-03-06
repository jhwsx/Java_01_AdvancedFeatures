package com.java.advanced.features.reflect.genericsclass.type.useingson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.java.advanced.features.reflect.genericsclass.type.useingson.my.MyTypeToken;
import com.java.advanced.features.reflect.genericsclass.type.useingson.you.YourTypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * 在 gson 中反序列化的应用
 */
public class Deserialize {

    public static void main(String[] args) {
        Gson gson = new Gson();
        // 使用 gson 对 Response 对象进行序列化
        Response<Data> response = new Response<>(new Data("dataContent"), 0, "success");
        String json = gson.toJson(response);
        System.out.println("json = " + json);
        // 再使用 gson 对 json 进行反序列化
        // 错误的写法
//        Response<Data> r = gson.fromJson(json, Response.class);
//        System.out.println(r);
//        Data data = r.data; // 23 行报错：Exception in thread "main" java.lang.ClassCastException: com.google.gson.internal.LinkedTreeMap cannot be cast to com.java.advanced.features.reflect.genericsclass.type.useingson.Deserialize$Data
//        // at com.java.advanced.features.reflect.genericsclass.type.useingson.Deserialize.main(Deserialize.java:23)
//        System.out.println(data);

        // 正确的写法
//        Type type = new TypeToken<Response<Data>>() {
//        }.getType();
//        System.out.println("type = " + type);
//        Response<Data> r = gson.fromJson(json, type);
//        System.out.println("r = " + r);
//        System.out.println("r.data.getClass() = " + r.data.getClass());

        // 自定义的写法（正确的）
        // 注意：这里是匿名内部类
        // 匿名内部类是一个继承了类或者实现了接口的子类匿名对象。
        Type type = new MyTypeToken<Response<Data>>() {
        }.getType();
        System.out.println("type = " + type);
        Response<Data> r = gson.fromJson(json, type);
        System.out.println("r = " + r);
        System.out.println("r.data.getClass() = " + r.data.getClass());


        Type type1 = new YourTypeToken<Response<Data>>() {
        }.getType();
        System.out.println("type1 = " + type1);
        Response<Data> r2 = gson.fromJson(json, type1);
        System.out.println("r2 = " + r2);
        System.out.println("r2.data.getClass() = " + r2.data.getClass());

        // 解析数组
        String json2 = "[\"Android\",\"Java\",\"Flutter\",\"Dart\"]";
        Type type2 = new MyTypeToken<List<String>>() {
        }.getType();
        System.out.println("type2 = " + type2);
        List<String> stringList = gson.fromJson(json2, type2);
        System.out.println(stringList);
    }


    static class Response<T> {
        T data;
        int code;
        String message;

        public Response(T data, int code, String message) {
            this.data = data;
            this.code = code;
            this.message = message;
        }

        @Override
        public String toString() {
            return "Response{" +
                    "data=" + data +
                    ", code=" + code +
                    ", message='" + message + '\'' +
                    '}';
        }
    }

    static class Data {
        String content;

        public Data(String content) {
            this.content = content;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "content='" + content + '\'' +
                    '}';
        }
    }
}
