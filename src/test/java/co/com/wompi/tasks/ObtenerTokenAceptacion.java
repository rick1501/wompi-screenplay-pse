package co.com.wompi.tasks;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;

import static co.com.wompi.utils.Constantes.*;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class ObtenerTokenAceptacion implements Task {

    public static ObtenerTokenAceptacion delMerchant() {
        return instrumented(ObtenerTokenAceptacion.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
            Get.resource(MERCHANT_ENDPOINT + LLAVE_PUBLICA)
                .with(req -> req.baseUri(BASE_URL))
        );
        // La respuesta se guarda en SerenityRest.lastResponse()
    }
}
