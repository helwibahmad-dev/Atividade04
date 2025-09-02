package br.persistencia;
import br.repositorio.CentralDeInformacoes;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Persistencia {

    public void salvarCentral(CentralDeInformacoes central, String nomeArquivo) {
        try {
            XStream xstream = new XStream(new StaxDriver());
            xstream.allowTypes(new Class[]{CentralDeInformacoes.class}); // para segurança
            FileOutputStream fos = new FileOutputStream(nomeArquivo);
            xstream.toXML(central, fos);
            fos.close();
        } catch (Exception e) {
            System.out.println("Erro ao salvar central: " + e.getMessage());
        }
    }

    public CentralDeInformacoes recuperarCentral(String nomeArquivo) {
        try {
            File arquivo = new File(nomeArquivo);
            if (!arquivo.exists()) {
                return new CentralDeInformacoes(); // retorna nova central se arquivo não existir
            }

            XStream xstream = new XStream(new StaxDriver());
            xstream.allowTypes(new Class[]{CentralDeInformacoes.class});
            FileInputStream fis = new FileInputStream(arquivo);
            CentralDeInformacoes central = (CentralDeInformacoes) xstream.fromXML(fis);
            fis.close();
            return central;

        } catch (Exception e) {
            System.out.println("Erro ao recuperar central: " + e.getMessage());
            return new CentralDeInformacoes();
        }
    }
}
