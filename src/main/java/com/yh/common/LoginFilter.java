package com.yh.common;

import com.yh.entity.AdminInfo;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@SuppressWarnings("all")
public class LoginFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			String uri=request.getRequestURI();
			String base=request.getContextPath();
			String carte_id=request.getParameter("carte_id");
			//获取当前用户
			AdminInfo admin=(AdminInfo) request.getSession().getAttribute("HFBACKSTAGEUSER");
			boolean flg=filterUrl(uri,base);

			//判断用户是否为空
			if (admin==null && flg) {
				if ((uri.endsWith(base) || uri.endsWith(base+"/") || uri.endsWith("/Index/index")) && carte_id==null) {
					request.getRequestDispatcher("/AdminInfo/login").forward(request, response);
				}else{
					request.getRequestDispatcher("/AdminInfo/goToLogin").forward(request, response);
				}
			}else{
				//如果地址是项目名则直接跳转到登录界面
				if (uri.endsWith(base) || uri.endsWith(base+"/")) {
					request.getRequestDispatcher("/AdminInfo/login").forward(request, response);
				}else{
					filterChain.doFilter(request, response);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 *********************************************************.<br>
	 * [方法] filterUrl <br>
	 * [描述] 过滤请求地址<直接放过的地址> <br>
	 * [参数] TODO(对参数的描述) <br>
	 * [返回] String <br>
	 * [时间] 2017-12-07 下午19:19:19 <br>
	 * [作者] lvl
	 *********************************************************.<br>
	 */
	public boolean filterUrl(String uri,String base){
		boolean flag=!uri.endsWith("dwz.frag.xml")
					&&!uri.endsWith("AdminInfo/getRandomImg")
					&&!uri.endsWith("AdminInfo/destroyLogin")
					&&!uri.endsWith("AdminInfo/")
					&&!uri.endsWith(".css")
					&&!uri.endsWith(".js")
					&&!uri.endsWith(".png")
					&&!uri.endsWith(".jpg")
					&&!uri.endsWith(".jpeg")
					&&!uri.endsWith(".bmp")
					&&!uri.endsWith(".gif")
					&&!uri.endsWith(".map")
					&&!uri.endsWith(".woff")
					&&!uri.endsWith(".ttf")
					&&!uri.endsWith("/AdminInfo/loginUser")
					&&!uri.endsWith("/AdminInfo/login")
					&&!uri.endsWith("/AdminInfo/goLogin")
				    &&!uri.endsWith("/signpos/getSign");

		return flag;
	}

}
