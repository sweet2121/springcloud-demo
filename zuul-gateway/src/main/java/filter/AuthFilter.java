package filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
认证服务是否完成，也就是用户是否登录
如果没有登录，不让请求后端的服务
 */
public class AuthFilter extends ZuulFilter {
    /**
     * 过滤类型
     * 设置在路由转发之前进行过滤
     *  filterType：返回一个字符串代表过滤器的类型，在zuul中定义了四种不同生命周期的过滤器类型，具体如下：
     *      * pre：路由之前
     *      * routing：路由之时
     *      * post： 路由之后
     *      * error：发送错误调用
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }
    /**
     * 过滤的顺序
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 这里可以写逻辑判断，是否要过滤，true,永远过滤。
     */
    @Override
    public boolean shouldFilter() {
        System.out.println("---shouldFilter----");
        //业务逻辑判断,首先获取request对象
        RequestContext requestContext = RequestContext.getCurrentContext() ;
        HttpServletRequest request = requestContext.getRequest();
        //获取请求的URL
        String requestURL = request.getRequestURI();
        System.out.println(requestURL);
        //放行User的请求
        if(requestURL.startsWith("/api/user")){
            return false;
        }
        return true;
    }

    /**
     * 过滤器的具体逻辑。
     * 可用很复杂，包括查sql，nosql去判断该请求到底有没有权限访问。
     */
    @Override
    public Object run() throws ZuulException {
        System.out.println("---run----");
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        String token = request.getParameter("token");

        if(StringUtils.isEmpty(token)){
            /** 表示不进行路由,不会被zuul转发到后端服务器 **/
            currentContext.setSendZuulResponse(false);
           /* * http状态码，标识未授权 **/
            currentContext.setResponseStatusCode(403);
            HttpServletResponse response = currentContext.getResponse();
            /** 设置返回的内容类型 **/
            response.setContentType("application/json;charset=utf-8");
            try {
                /** 输出返回结果 **/
                response.getWriter().write("{\"message\":\"未授权\"}");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
