package cn.yuntangnet.duizhang.config;

import com.catt.ipnet.confsrv.client.IpnetRestFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 张茂林
 * @since 2018/2/26 15:08
 */
@Configuration
public class CommonConfig {
    @Bean
    public Converter<String, Date> convertStrToDate() {
        return new Converter<String, Date>() {
            @Override
            public Date convert(String source) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = null;
                try {
                    date = sdf.parse(source);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return date;
            }
        };
    }

    @Value("${serverUrl}")
    private String serverUrl;
    @Bean
    public IpnetRestFactory initFactory(){
        return IpnetRestFactory.getInstance(serverUrl);
    }

}
