import java.util.*;

import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;

public class TranslatorVisitor extends RenderingBaseVisitor<Void> {
  Stack<String> closer = new Stack<String>();
  int flag_star = 0;
  int flag_sharp = 0;
  int former_level = 0;

  public Void flag_reset() {
    flag_sharp = 0;
    flag_star = 0;

    return null;
  }

  public Void stack_reset() {
    while (!closer.isEmpty()) {
      System.out.println(closer.pop());
    }

    return null;
  }

  @Override public Void visitDocument(RenderingParser.DocumentContext ctx) {
    System.out.println("<html>");
    this.visitBlocks(ctx.blocks());
    stack_reset();
    System.out.println("</html>");
    return null;
  }

  @Override public Void visitBlocks(RenderingParser.BlocksContext ctx) {
    for(RenderingParser.BlockContext blockContext : ctx.block()) {
      this.visit(blockContext);
    };
    return null;
  }

  @Override public Void visitPLAINTEXT(RenderingParser.PLAINTEXTContext ctx) {
    flag_reset();
    stack_reset();
    System.out.println(ctx.TEXT().getText());
    return null;
  }

  @Override public Void visitSTAR(RenderingParser.STARContext ctx) {
    int current_level = ctx.STARs().getText().length();

    // 새 리스트가 시작된 경우
    if (flag_sharp == 1 && former_level == current_level) {
      stack_reset();
      flag_star = 1;
      flag_sharp = 0;
      System.out.println("<ul>");
      closer.push("</ul>");
    } 
    
    // 단계가 증가한 경우
    else if (former_level < current_level) {
      flag_star = 1;
      flag_sharp = 0;
      System.out.println("<ul>");
      closer.push("</ul>");
      former_level = current_level;
    }

    // 단계가 감소한 경우
    else if (former_level > current_level) {
      flag_star = 1;
      flag_sharp = 0;

      for (int i = 0; i < former_level - current_level; i++) {
        System.out.println(closer.pop());
      }

      former_level = current_level;
    }
    
    System.out.println("<li>" + ctx.TEXT().getText() + " </li>");

    return null;
  }

  @Override public Void visitSHARP(RenderingParser.SHARPContext ctx) {
    int current_level = ctx.SHARPs().getText().length();
    
    // 새 리스트가 시작된 경우
    if (flag_star == 1 && former_level == current_level) {
      stack_reset();
      flag_star = 0;
      flag_sharp = 1;
      System.out.println("<ol>");
      closer.push("</ol>");
    }

    // 단계가 증가한 경우
    else if (former_level < current_level) {
      flag_star = 0;
      flag_sharp = 1;
      System.out.println("<ol>");
      closer.push("</ol>");
      former_level = current_level;
    }

    // 단계가 줄어든 경우
    else if (former_level > current_level) {
      flag_star = 0;
      flag_sharp = 1;

      for (int i = 0; i < former_level - current_level; i++) {
        System.out.println(closer.pop());
      }

      former_level = current_level;
    }

    System.out.println("<li>" + ctx.TEXT().getText() + " </li>");

    return null;
  }

  @Override public Void visitHEADER(RenderingParser.HEADERContext ctx) {
    flag_reset();
    stack_reset();
    int open = ctx.EQs(0).getText().length();
    int close = ctx.EQs(1).getText().length();
    if (open == close && open <= 6) {
      System.out.print("<h"+open+">");
      System.out.print(ctx.TEXT().getText());
      System.out.println("</h"+close+">");
    } else {
      System.out.print(ctx.EQs(0).getText());
      System.out.print(ctx.TEXT().getText());
      System.out.println(ctx.EQs(1).getText());
    }
    return null;
  }

  @Override public Void visitINDENT(RenderingParser.INDENTContext ctx) {
    flag_reset();
    stack_reset();
    int num = ctx.COLONs().getText().length();

    if (num <= 6) {
      System.out.print("<p style=\"margin-left:" + (num * 2) + "em\">");
      System.out.print(ctx.TEXT().getText());
      System.out.println("</p>");
    } else {
      System.out.print(ctx.COLONs().getText());
      System.out.println(ctx.TEXT().getText());
    }
    
    return null;
  }
}