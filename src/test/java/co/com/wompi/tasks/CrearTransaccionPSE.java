package co.com.wompi.tasks;

import io.restassured.http.ContentType;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;
import org.apache.commons.codec.digest.DigestUtils;
import org.json.JSONObject;

import static co.com.wompi.utils.Constantes.*;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class CrearTransaccionPSE implements Task {

    private final String acceptanceToken;
    private final String doc;

    public CrearTransaccionPSE(String acceptanceToken, String doc) {
        this.acceptanceToken = acceptanceToken;
        this.doc = doc;
    }

    public static CrearTransaccionPSE valida(String acceptanceToken) {
        return instrumented(CrearTransaccionPSE.class, acceptanceToken, "1061779338");
    }

    public static CrearTransaccionPSE conDocumento(String acceptanceToken, String documento) {
        return instrumented(CrearTransaccionPSE.class, acceptanceToken, documento);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        long amountInCents = 500000; // 5,000 COP
        String currency = "COP";
        String reference = "ref-" + System.currentTimeMillis();

        // Generar firma de integridad
        String signature = DigestUtils.sha256Hex(reference + amountInCents + currency + LLAVE_INTEGRIDAD);

        // Método de pago PSE
        JSONObject paymentMethod = new JSONObject();
        paymentMethod.put("type", "PSE");
        paymentMethod.put("user_type", 0);

        // Condicional para forzar error cuando el documento sea "inválido"
        if (!doc.equals("1061779338")) {
            // Datos inválidos para provocar 422
            paymentMethod.put("user_legal_id_type", "XX"); // tipo inválido
            paymentMethod.put("financial_institution_code", "9999"); // código inválido
        } else {
            // Datos válidos
            paymentMethod.put("user_legal_id_type", "CC");
            paymentMethod.put("financial_institution_code", "1001"); // banco sandbox
        }

        paymentMethod.put("user_legal_id", doc);
        paymentMethod.put("payment_description", "Compra de prueba PSE");

        // Body principal
        JSONObject data = new JSONObject();
        data.put("amount_in_cents", amountInCents);
        data.put("currency", currency);
        data.put("customer_email", "qa.wompi@test.com");
        data.put("payment_method", paymentMethod);
        data.put("reference", reference);
        data.put("acceptance_token", acceptanceToken);
        data.put("signature", signature);

        // POST
        actor.attemptsTo(
                Post.to(TRANSACTIONS_ENDPOINT)
                        .with(request -> request
                                .baseUri(BASE_URL)
                                .contentType(ContentType.JSON)
                                .auth().oauth2(LLAVE_PRIVADA)
                                .body(data.toString())
                        )
        );

        // Debug
        System.out.println("Response: " + SerenityRest.lastResponse().asString());
        System.out.println("Status code: " + SerenityRest.lastResponse().statusCode());
    }
}
