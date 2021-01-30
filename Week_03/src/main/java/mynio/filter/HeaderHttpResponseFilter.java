package mynio.filter;

import io.netty.handler.codec.http.FullHttpResponse;

public class HeaderHttpResponseFilter implements HttpResponseFilter {
    @Override
    public void filter(FullHttpResponse response) {
        //对responsse进行处理
        response.headers().set("ccc","ddd");
    }
}
