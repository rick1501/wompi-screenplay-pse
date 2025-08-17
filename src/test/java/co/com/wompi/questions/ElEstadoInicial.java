package co.com.wompi.questions;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import org.json.JSONObject;

public class ElEstadoInicial implements Question<String> {

    public static ElEstadoInicial deLaTransaccion() {
        return new ElEstadoInicial();
    }

    @Override
    public String answeredBy(Actor actor) {
        JSONObject response = new JSONObject(SerenityRest.lastResponse().getBody().asString());
        // Permite CREATED o PENDING en sandbox
        return response.getJSONObject("data").getString("status");
    }
}
