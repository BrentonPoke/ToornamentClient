import com.toornament.ToornamentClient;
import com.toornament.concepts.Webhooks;
import com.toornament.model.Webhook;
import com.toornament.model.enums.Scope;
import com.toornament.model.request.WebhookQuery;
import com.toornament.model.request.WebhookQuery.WebhookQueryBuilder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebhooksTests {
  private ToornamentClient client;
  private HashSet<Scope> scopes = new HashSet<>();
  private WebhookQuery.WebhookQueryBuilder queryBuilder = WebhookQuery.builder();
  Logger logger = LoggerFactory.getLogger(this.getClass());
  URL url;
  BufferedReader sc;

  @Before
  public void Setup() {
    scopes.add(Scope.ORGANIZER_RESULT);
    scopes.add(Scope.ORGANIZER_PERMISSION);
    scopes.add(Scope.ORGANIZER_VIEW);
    client =
        new ToornamentClient(
            System.getenv("KEY"), System.getenv("CLIENT"), System.getenv("SECRET"), scopes);
    client.authorize();
    try{
    url = new URL("http://68.41.124.183");
    sc = new BufferedReader(new InputStreamReader(url.openStream()));
    queryBuilder.url(sc.readLine().trim()).enabled(false).name("Test webhook");
    }catch (IOException e){
        logger.info("Exception type: {} - {}",e.getClass(),e.getMessage());
    }
  }

  @Test
  public void WebhookSecretTest() {
Webhooks webhook = new Webhooks(client);
      try {
          webhook.createWebhook(queryBuilder.build());
      } catch (IOException e) {
          e.printStackTrace();
      }
  }
}
