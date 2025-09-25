package gerenciamentorestaurante.projeto1.enumerator;

public enum GrupoEnum {
INGREDIENTE(1), FICHATECNICA(2);
private final int grupo;
	private GrupoEnum(int codigo) {
		this.grupo = codigo;
	}
    public int getCodigo() { return grupo;
    }
}
