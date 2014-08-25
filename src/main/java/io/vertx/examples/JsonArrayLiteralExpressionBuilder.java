package io.vertx.examples;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

/**
 * @author <a href="mailto:julien@julienviet.com">Julien Viet</a>
 */
public class JsonArrayLiteralExpressionBuilder extends ExpressionBuilder {

  private final BiConsumer<List<ExpressionBuilder>, Renderer> renderer;
  private String member;
  private List<ExpressionBuilder> values = new ArrayList<>();

  public JsonArrayLiteralExpressionBuilder(BiConsumer<List<ExpressionBuilder>, Renderer> renderer) {
    this.renderer = renderer;
  }

  @Override
  public ExpressionBuilder onMemberSelect(String identifier) {
    this.member = identifier;
    return this;
  }

  @Override
  public ExpressionBuilder onMethodInvocation(List<ExpressionBuilder> arguments) {
    switch (member) {
      case "addString":
      case "addBoolean":
      case "addNumber":
      case "addArray":
      case "addObject":
        values.add(arguments.get(0));
        break;
      default:
        throw new UnsupportedOperationException("Method " + member + " not yet implemented");
    }
    return this;
  }

  @Override
  public void render(Renderer renderer) {
    this.renderer.accept(values, renderer);
  }
}