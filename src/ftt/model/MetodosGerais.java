package ftt.model;

import java.util.ArrayList;
import java.util.List;

import ftt.enums.EnumMatStatus;
import ftt.enums.EnumNivelCurso;
import ftt.enums.EnumPeriodo;

public class MetodosGerais {

	public static Endereco mudaStringParaEndereco(String ender) {
		Endereco objEndereco = new Endereco();
		if (ender != null) {
			String[] aux = ender.split("\\|");

			objEndereco.setRua(aux[0]);
			objEndereco.setComplemento(aux[1]);
			objEndereco.setNumero(aux[2]);
			objEndereco.setBairro(aux[3]);
			objEndereco.setCidade(aux[4]);
			objEndereco.setUf(aux[5]);
		}

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

	public static EnumNivelCurso stringParaEnumNivel(String strNivel) {
		if (strNivel.equals(EnumNivelCurso.BASICO.getNivelCurso())) {
			return EnumNivelCurso.BASICO;
		} else if (strNivel.equals(EnumNivelCurso.INTERMEDIARIO.getNivelCurso())) {
			return EnumNivelCurso.INTERMEDIARIO;
		} else {
			return EnumNivelCurso.AVANCADO;
		}
	}

	public static EnumPeriodo stringParaPeriodo(String strPeriodo) {

		if (strPeriodo.equals(EnumPeriodo._3_MESES.getPeriodoCurso())) {
			return EnumPeriodo._3_MESES;
		} else if (strPeriodo.equals(EnumPeriodo._6_MESES.getPeriodoCurso())) {
			return EnumPeriodo._6_MESES;
		} else if (strPeriodo.equals(EnumPeriodo._9_MESES.getPeriodoCurso())) {
			return EnumPeriodo._9_MESES;
		} else {
			return EnumPeriodo._12_MESES;
		}
	}

	public static List<Horario> stringParaListaHorarios(String strHorarios) {
		strHorarios+=">";	
		List<Horario> horarios = new ArrayList<>();
		if (strHorarios != null) {
			String[] aux = strHorarios.split("\\>");
			for (String hora : aux) {
				String[] horas = hora.split("\\-");
				Horario h = new Horario();
				h.setDiaSemana(horas[0].trim());
				h.setFaixaHorario(horas[1].trim());
				horarios.add(h);
			}
		}
		return horarios;
	}

	public static String listaHorariosParaString(List<Horario> listaHorarios) {
		StringBuilder sb = new StringBuilder();
		int cont = 0;
		for (Horario hor : listaHorarios) {
			cont += 1;
			sb.append(hor.getDiaSemana()).append("-")
			.append(hor.getFaixaHorario());
			if (cont != listaHorarios.size()) {
				sb.append(">");
			}
		}
		return sb.toString();
	}
	
	public static EnumMatStatus stringParaEnumMatStatus(String strMatStatus) {
		if (strMatStatus.equals(EnumMatStatus.ATIVA.getMatStatusString())) {
			return EnumMatStatus.ATIVA;
		}  else {
			return EnumMatStatus.INATIVA;
		}
	}
}
