package edu.radyuk.foodblog.tag;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class PagesTag extends TagSupport {
    private static final Logger logger = LogManager.getLogger();
    private String pagesCountAttribute;
    private String currentPageAttribute;
    private String searchAttribute;
    private String command;

    public void setCurrentPageAttribute(String currentPageAttribute) {
        this.currentPageAttribute = currentPageAttribute;
    }

    public void setSearchAttribute(String searchAttribute) {
        this.searchAttribute = searchAttribute;
    }

    public void setPagesCountAttribute(String pagesCountAttribute) {
        this.pagesCountAttribute = pagesCountAttribute;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    @Override
    public int doStartTag() throws JspException {
        int pageCount;
        int page;
        try {
            pageCount = Integer.parseInt(pagesCountAttribute);
            page = Integer.parseInt(currentPageAttribute);
        } catch (NumberFormatException e) {
            logger.log(Level.ERROR, "Invalid attribute value");
            throw new JspException(e);
        }
        if (pageCount == 1) {
            return SKIP_BODY;
        }

        try {
            StringBuilder jspCodeTemplate =
                    new StringBuilder("<nav aria-label=\"...\">\n" +
                            "    <ul class=\"pagination pagination-lg\">\n");


            String contextPath = pageContext.getServletContext().getContextPath();

            String pageTemplate = "<li class=\"page-item\"><a class=\"page-link\" href=\""
                    + contextPath + "/controller?command=" + command + "&page=%d&search=" + searchAttribute +
                    "\">%d</a></li>";

            String activePageTemplate = "<li class=\"page-item active\"><a class=\"page-link\" href=\""
                    + contextPath + "/controller?command=" + command + "&page=%d&search=" + searchAttribute +
                    "\">%d</a></li>";

            for (int i = 1; i <= pageCount; i++) {
                if (i == page) {
                    jspCodeTemplate.append(String.format(activePageTemplate, i, i));
                } else {
                    jspCodeTemplate.append(String.format(pageTemplate, i, i));
                }
            }
            jspCodeTemplate.append("</ul>\n </nav>\n");
            pageContext.getOut().write(jspCodeTemplate.toString());
        } catch (IOException e) {
            logger.log(Level.ERROR, "Error while writing to stream");
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }
}
