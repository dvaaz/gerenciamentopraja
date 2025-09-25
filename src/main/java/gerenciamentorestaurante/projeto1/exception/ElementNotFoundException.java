package gerenciamentorestaurante.projeto1.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ElementNotFoundException extends RuntimeException {

    /**
     * Construtor padrão.
     */
    public ElementNotFoundException() {
        super("Recurso não encontrado.");
    }

    /**
     * Construtor que aceita uma mensagem de erro específica.
     *
     * @param mensagem A mensagem de erro detalhada.
     */
    public ElementNotFoundException(String mensagem) {
        super(mensagem);
    }

    /**
     * Construtor que aceita uma mensagem de erro e a causa original da exceção.
     *
     * @param mensagem A mensagem de erro detalhada.
     * @param causa A causa original (exceção aninhada).
     */
    public ElementNotFoundException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}

