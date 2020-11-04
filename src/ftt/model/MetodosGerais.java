package ftt.model;

public class MetodosGerais {

	public static Endereco mudaStringParaEndereco(String ender) {
		Endereco objEndereco = new Endereco();
		String[] aux = ender.split("\\|");

		objEndereco.setRua(aux[0]);
		objEndereco.setComplemento(aux[1]);
		objEndereco.setNumero(aux[2]);
		objEndereco.setBairro(aux[3]);
		objEndereco.setCidade(aux[4]);
		objEndereco.setUf(aux[5]);

		return objEndereco;
	}

	public static String mudaEnderecoParaString(Endereco end) {
		StringBuilder sb = new StringBuilder();
		sb.append(end.getRua()).append("|");
		sb.append(end.getComplemento()).append("|");
		sb.append(end.getNumero()).append("|");
		sb.append(end.getBairro()).append("|");
		sb.append(end.getCidade()).append("|");
		sb.append(end.getUf());

		return sb.toString();
	}

}
