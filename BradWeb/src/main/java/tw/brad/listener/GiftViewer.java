package tw.brad.listener;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tw.brad.apis.Gift;

@WebServlet("/GiftViewer")
public class GiftViewer extends HttpServlet {
	private static final String TEMPLATE_PATH = "/WEB-INF/views/view1.html";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			List<Gift> gifts = (List<Gift>)request.getAttribute("gifts");
			Integer prev = (Integer)request.getAttribute("prev");
			Integer page = (Integer)request.getAttribute("page");
			Integer next = (Integer)request.getAttribute("next");
			
			String template = readTemplate(TEMPLATE_PATH);
			System.out.println(template);
			
			String tableHTML = readTableHTML(gifts);
			
			String html = template
						.replace("{{TITLE}}", "Brad Big Company")
						.replace("{{TABLE}}", tableHTML)
						.replace("{{PREV}}", prev.toString())
						.replace("{{PAGE}}", page.toString())
						.replace("{{NEXT}}", next.toString());
			
			response.setContentType("text/html; charset=UTF-8");
			response.getWriter().write(html);
			response.flushBuffer();
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private String readTemplate(String path) throws Exception{
		System.out.println(getServletContext().getContextPath());
		try (InputStream in = getServletContext().getResourceAsStream(path);
				BufferedInputStream bin = new BufferedInputStream(in)){
			if (bin != null) {
				return new String(bin.readAllBytes(), StandardCharsets.UTF_8);
			}else {
				System.out.println("Templete NOT FOUND");
				throw new Exception("Templete NOT FOUND");
			}
		}
	}
	
	private String readTableHTML(List<Gift> gifts) {
		StringBuilder sb = new StringBuilder();
		sb.append("<table border='1'>");
			sb.append("<caption>").append("伴手禮列表").append("</caption>");
			sb.append("<thead><tr>");
				sb.append("<th>編號</th>");
				sb.append("<th>名稱</th>");
				sb.append("<th>特色</th>");
				sb.append("<th>地址</th>");
				sb.append("<th>電話</th>");
			sb.append("</tr></thead>");
			
			sb.append("<tbody>");
				for (Gift gift: gifts) {
					sb.append("<tr>");
						sb.append(String.format("<td>%d</td>", gift.getId()));
						sb.append(String.format("<td>%s</td>", gift.getName()));
						sb.append(String.format("<td>%s</td>", gift.getFeature()));
						sb.append(String.format("<td>%s</td>", gift.getAddr()));
						sb.append(String.format("<td>%s</td>", gift.getTel()));
					sb.append("</tr>");
				}
			sb.append("</tbody>");
		sb.append("</table>");
		
		return sb.toString();
	}
	
	
}