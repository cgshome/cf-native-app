package paasxpert.demo.cf.datasource;

import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.util.ReflectionUtils;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;

public class ParseUtil {
	
	   public static String toString(DataSource dataSource) {
	        if (dataSource == null) {
	            return "<none>";
	        } else {
	            try {
	                Field urlField = ReflectionUtils.findField(dataSource.getClass(), "url");
	                ReflectionUtils.makeAccessible(urlField);
	                return stripCredentials((String) urlField.get(dataSource));
	            } catch (Exception fe) {
	                try {
	                    Method urlMethod = ReflectionUtils.findMethod(dataSource.getClass(), "getUrl");
	                    ReflectionUtils.makeAccessible(urlMethod);
	                    return stripCredentials((String) urlMethod.invoke(dataSource, (Object[])null));
	                } catch (Exception me){
	                    return "<unknown> " + dataSource.getClass();                    
	                }
	            }
	        }
	    }

	public static String toString(RedisConnectionFactory redisConnectionFactory) {
		if (redisConnectionFactory == null) {
			return "<none>";
		} else {
			if (redisConnectionFactory instanceof JedisConnectionFactory) {
				JedisConnectionFactory jcf = (JedisConnectionFactory) redisConnectionFactory;
				return jcf.getHostName().toString() + ":" + jcf.getPort();
			} else if (redisConnectionFactory instanceof LettuceConnectionFactory) {
				LettuceConnectionFactory lcf = (LettuceConnectionFactory) redisConnectionFactory;
				return lcf.getHostName().toString() + ":" + lcf.getPort();
			}
			return "<unknown> " + redisConnectionFactory.getClass();
		}
	}

	    private static String stripCredentials(String urlString) {
	        try {
	            if (urlString.startsWith("jdbc:")) {
	                urlString = urlString.substring("jdbc:".length());
	            }
	            URI url = new URI(urlString);
	            return new URI(url.getScheme(), null, url.getHost(), url.getPort(), url.getPath(), null, null).toString();
	        }
	        catch (URISyntaxException e) {
	            System.out.println(e);
	            return "<bad url> " + urlString;
	        }
	    }
	    
}
