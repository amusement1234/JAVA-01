package mynio.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaders;

public class ProxyBizFilter implements HttpRequestFilter {
    @Override
    public void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        String uri = fullRequest.uri();
//        System.out.println("ProxyBizFilter接收到的请求,url: " + uri);
        HttpHeaders headers = fullRequest.headers();
        if (headers == null) {
            headers = new DefaultHttpHeaders();
        }
        headers.add("ccc", "ddd");
    }
}
