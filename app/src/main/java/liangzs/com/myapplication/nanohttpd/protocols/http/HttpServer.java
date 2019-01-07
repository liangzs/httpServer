package liangzs.com.myapplication.nanohttpd.protocols.http;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import liangzs.com.myapplication.nanohttpd.protocols.http.response.Response;

/**
 * @author liangzs
 * @Date 2019/1/7
 */
public class HttpServer extends NanoHTTPD {
    private HttpController httpController;

    public HttpServer(int port) {
        super(port);
        httpController = new HttpController();
    }

    public HttpServer(String hostname, int port) {
        super(hostname, port);
    }

    @Override
    public Response handle(IHTTPSession session) {
        return super.handle(session);
    }

    @Override
    protected Response serve(IHTTPSession session) {
        Response response = Response.newFixedLengthResponse("valide request please check uri" + session.getUri());
        for (Method method : httpController.getClass().getMethods()) {
            HttpMethod annotaion = method.getAnnotation(HttpMethod.class);
            if (annotaion != null&&annotaion.value().equals(session.getUri())) {
                try {
                    response = (Response) method.invoke(httpController, session.getParameters());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
        return response;
    }
}
