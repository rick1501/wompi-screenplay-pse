package co.com.wompi.questions;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Question;

public class ElCodigoDeRespuesta implements Question<Integer> {

    public static ElCodigoDeRespuesta es() {
        return new ElCodigoDeRespuesta();
    }

    @Override
    public Integer answeredBy(net.serenitybdd.screenplay.Actor actor) {
        return SerenityRest.lastResponse().getStatusCode();
    }
}
