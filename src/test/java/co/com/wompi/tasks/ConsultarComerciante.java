package co.com.wompi.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static co.com.wompi.utils.Constantes.*;

public class ConsultarComerciante implements Task {

    private final String llavePublica;

    public ConsultarComerciante(String llavePublica) {
        this.llavePublica = llavePublica;
    }

    public static ConsultarComerciante conLlave(String llavePublica) {
        return instrumented(ConsultarComerciante.class, llavePublica);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
            Get.resource(MERCHANT_ENDPOINT + llavePublica)
                .with(req -> req.baseUri(BASE_URL))
        );
    }
}
