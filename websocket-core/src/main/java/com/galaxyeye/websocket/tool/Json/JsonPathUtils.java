package com.galaxyeye.websocket.tool.Json; /*
 * Description:com.galaxyeye.websocket.tool.Json
 * @Date Create on 16:15
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/9/8日galaxyeye All Rights Reserved.
 */
import com.galaxyeye.websocket.test.galaxyeye.Article.DTO.MainTypeListDTO;
import com.jayway.jsonpath.*;
import com.jayway.jsonpath.spi.json.JacksonJsonProvider;
import com.jayway.jsonpath.spi.json.JsonProvider;
import com.jayway.jsonpath.spi.mapper.JacksonMappingProvider;
import com.jayway.jsonpath.spi.mapper.MappingProvider;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
@Slf4j
public class JsonPathUtils{
    private static final JsonProvider jsonProvider = new JacksonJsonProvider();
    private static final MappingProvider mappingProvider = new JacksonMappingProvider();

    static {
        Configuration.setDefaults(new Configuration.Defaults() {

            @Override
            public JsonProvider jsonProvider() {
                return jsonProvider;
            }
            @Override
            public MappingProvider mappingProvider() {
                return mappingProvider;
            }
            @Override
            public Set<Option> options() {
                return EnumSet.noneOf(Option.class);
            }
        });
    }




    public static <T> T parse(String json, String expression,TypeRef<T> typeRef){
        return JsonPath.parse(json).read(expression, typeRef);
    }


    public static <T> T parse(String json, String expression,Class<T> clz)  {
        return JsonPath.parse(json).read(JsonPath.compile(expression), clz);
    }

    @Test
    public void test3() throws Exception {
        String mainTypeListResult="{\"count\":1,\"list\":[{\"id\":\"151\",\"is_maintained\":\"2\",\"main_type_code\":\"01000151\",\"main_type_name\":\"yy\"}],\"msg\":\"ok\",\"page\":1,\"result\":1,\"seq\":null}";
        String gf="{\n" +
                "            \"id\":\"151\",\n" +
                "            \"is_maintained\":\"2\",\n" +
                "            \"main_type_code\":\"01000151\",\n" +
                "            \"main_type_name\":\"yy\"\n" +
                "        }";
        MainTypeListDTO.MainTypeDTO mainTypeDTO =JsonPathUtils.parse(gf,"$",MainTypeListDTO.MainTypeDTO.class);
        System.out.println(mainTypeDTO.getIs_maintained());

        MainTypeListDTO.MainTypeDTO mainTypeDTO1 =JsonPathUtils.parse(gf,"$",MainTypeListDTO.MainTypeDTO.class);
        System.out.println(mainTypeDTO1);

        MainTypeListDTO mainTypeListDTO1 =JsonPathUtils.parse(mainTypeListResult,"$",MainTypeListDTO.class);
        System.out.println(mainTypeListDTO1);

        MainTypeListDTO mainTypeListDTO2 =JsonPathUtils.parse(mainTypeListResult,"$",new MainTypeListDTO().getClass());
        System.out.println(mainTypeListDTO2);

        ArrayList<MainTypeListDTO.MainTypeDTO> mainTypeDTO2 =JsonPathUtils.parse(mainTypeListResult,"$.list[*]",new ArrayList<MainTypeListDTO.MainTypeDTO>(){}.getClass());
        System.out.println(mainTypeDTO2);

        List<MainTypeListDTO.MainTypeDTO> mainTypeList1 = JsonPathUtils.parse(mainTypeListResult,"$.list[*]",new ArrayList<MainTypeListDTO.MainTypeDTO>(){}.getClass());
        System.out.println(mainTypeList1.get(0).getIs_maintained());
    }



}
