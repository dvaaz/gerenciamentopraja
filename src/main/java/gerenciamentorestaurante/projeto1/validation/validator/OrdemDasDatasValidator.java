package gerenciamentorestaurante.projeto1.validation.validator;

import gerenciamentorestaurante.projeto1.validation.anotation.OrdemDasDatas;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.reflect.Method;
import java.util.Date;

public class OrdemDasDatasValidator implements ConstraintValidator<OrdemDasDatas, Object> {
    // campos para carregar os valores a serem comparados
    private String primeiraData;
    private String segundaData;

    @Override
    public void initialize(OrdemDasDatas constraintAnnotation) {
        this.primeiraData = constraintAnnotation.primeiraData();
        this.segundaData = constraintAnnotation.segundaData();
    }
//trecho feito com auxilio
    @Override
    public boolean isValid(Object object, ConstraintValidatorContext context) {
        try{
    Class<?> clazz = object.getClass();

    Method getterInicial = clazz.getMethod("get"+ capitalize(primeiraData));
    Method getterFinal = clazz.getMethod("get"+capitalize(segundaData));
    
    Date dataInicial = (Date) getterInicial.invoke(object);
    Date dataFinal = (Date) getterFinal.invoke(object);

    //true = possibilita a edição de data após o cadastro
    if (dataInicial == null || dataFinal == null) {return true;}

    // !dataFinal.before(dataInicial) retorna dataFinal >= dataInicial,
            // ao contrário de dataInicial.before(dataFinal) que retorna dataInicial < dataFinal
    return !dataFinal.before(dataInicial);
    } catch(Exception e){
            return false;
        }
    }
    private String capitalize(String nome) {
        return nome.substring(0, 1).toUpperCase() + nome.substring(1);
    }
}