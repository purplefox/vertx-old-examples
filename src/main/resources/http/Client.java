package http;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.http.RequestOptions;

/**
 * @author <a href="mailto:julien@julienviet.com">Julien Viet</a>
 */
public class Client extends AbstractVerticle {

  @Override
  public void start() throws Exception {
    vertx.createHttpClient(HttpClientOptions.options()).getNow(RequestOptions.options().setHost("localhost").setPort(8080).setRequestURI("/"), resp -> {
      System.out.println("Got response " + resp.statusCode());
      resp.bodyHandler(body -> {
        System.out.println("Got data " + body.toString("ISO-8859-1"));
      });
    });
  }
}