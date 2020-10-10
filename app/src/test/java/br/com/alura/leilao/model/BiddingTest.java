package br.com.alura.leilao.model;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

import br.com.alura.leilao.exceptions.LanceFoiMenorQueMaiorLanceException;
import br.com.alura.leilao.exceptions.MesmoUsuarioDoUltimoLanceException;
import br.com.alura.leilao.exceptions.UsuarioJaFez5LancesException;

import static org.junit.Assert.*;

public class BiddingTest {

    public static final double DELTA = 0.0001;
    private final Bidding BIDDING = new Bidding("Macbook");
    private final User ARTHUR = new User("Arthur");
    private final User MARIANE = new User("Mari");

    @Test
    public void getDescricao() {

        String descricaoDevolvida = BIDDING.getDescription();

        assertEquals("Macbook",descricaoDevolvida);
    }
    @Test
    public void getMaiorLance_QuandoRecebeApenasUmLance() {
        BIDDING.propoe(new Bid(ARTHUR,150.00));
        double valorDevolvido = BIDDING.getHighestBid();

        assertEquals(valorDevolvido,150.00, DELTA);
    }

    //Teste para pegar os três maiores lances quando receber exatos três lances
    @Test
    public void deve_DevolverTresMaioresLances_QuandoRecebeExatosTresLances(){
        BIDDING.propoe(new Bid(ARTHUR,150.00));
        BIDDING.propoe(new Bid(MARIANE,240.00));
        BIDDING.propoe(new Bid(ARTHUR,350.00));

        List<Bid> tresMaioresLancesDevolvidos = BIDDING.tresMaioresLances();

        assertEquals(3,tresMaioresLancesDevolvidos.size()); // Verifica se tem 3 lances
        assertEquals(350.00,tresMaioresLancesDevolvidos.get(0).getValue(), DELTA);
        assertEquals(240.00,tresMaioresLancesDevolvidos.get(1).getValue(), DELTA);
        assertEquals(150.00,tresMaioresLancesDevolvidos.get(2).getValue(), DELTA);
    }

    @Test
    public void nãoDeve_DevolverLances_QuandoNãoHáLances(){
        List<Bid> tresMaioresLancesDevolvidos = BIDDING.tresMaioresLances();

        assertEquals(0,tresMaioresLancesDevolvidos.size()); // Verifica se tem 3 lances
    }

    @Test
    public void deve_DevolverUmLance_QuandoHáApenasUmLance(){
        BIDDING.propoe(new Bid(ARTHUR,150.00));

        List<Bid> tresMaioresLancesDevolvidos = BIDDING.tresMaioresLances();

        assertEquals(1,tresMaioresLancesDevolvidos.size()); // Verifica se tem 3 lances
        assertEquals(150.00,tresMaioresLancesDevolvidos.get(0).getValue(), DELTA);
    }

    @Test
    public void deve_DevolverDoisLances_QuandoHáApenasDoisLances(){
        BIDDING.propoe(new Bid(ARTHUR,150.00));
        BIDDING.propoe(new Bid(MARIANE,200.00));

        List<Bid> tresMaioresLancesDevolvidos = BIDDING.tresMaioresLances();

        assertEquals(2,tresMaioresLancesDevolvidos.size()); // Verifica se tem 3 lances
        assertEquals(200.00,tresMaioresLancesDevolvidos.get(0).getValue(), DELTA);
        assertEquals(150.00,tresMaioresLancesDevolvidos.get(1).getValue(), DELTA);
    }

    @Test
    public void deve_DevolverTrêsMaioresLances_QuandoHáQuatroLances(){
        BIDDING.propoe(new Bid(ARTHUR,150.00));
        BIDDING.propoe(new Bid(MARIANE,200.00));
        BIDDING.propoe(new Bid(ARTHUR,380.00));
        BIDDING.propoe(new Bid(MARIANE,450.00));

        List<Bid> tresMaioresLancesDevolvidos = BIDDING.tresMaioresLances();

        assertEquals(3,tresMaioresLancesDevolvidos.size()); // Verifica se tem 3 lances
        assertEquals(450.00,tresMaioresLancesDevolvidos.get(0).getValue(), DELTA);
        assertEquals(380.00,tresMaioresLancesDevolvidos.get(1).getValue(), DELTA);
        assertEquals(200.00,tresMaioresLancesDevolvidos.get(2).getValue(), DELTA);
    }

    @Test
    public void deve_DevolverZero_QuandoEstiverSemMaioresLances(){
        double valorDevolvido = BIDDING.getHighestBid();

        assertEquals(0.0,valorDevolvido, DELTA);
    }

    @Test
    public void deve_DevolverZero_QuandoEstiverSemMenoresLances(){
        double valorDevolvido = BIDDING.getLowestBid();

        assertEquals(0.0,valorDevolvido, DELTA);
    }

    @Test(expected = LanceFoiMenorQueMaiorLanceException.class)
    public void deve_LançarException_QuandoForMenorQueOMaiorLance(){
        BIDDING.propoe(new Bid(ARTHUR,380.00));
        BIDDING.propoe(new Bid(MARIANE, 50.00));
    }

    @Test(expected = MesmoUsuarioDoUltimoLanceException.class)
    public void deve_LançarException_QuandoForOMesmoUsuarioDoUltimoLance(){
        BIDDING.propoe(new Bid(ARTHUR,380.00));
        BIDDING.propoe(new Bid(new User("Arthur"), 420.00));
    }

    @Test(expected = UsuarioJaFez5LancesException.class)
    public void deve_LançarException_QuandoUsuarioDerCincoLances(){
        BIDDING.propoe(new Bid(ARTHUR,380.00));
        BIDDING.propoe(new Bid(MARIANE,390.00));
        BIDDING.propoe(new Bid(ARTHUR,400.00));
        BIDDING.propoe(new Bid(MARIANE,410.00));
        BIDDING.propoe(new Bid(ARTHUR,420.00));
        BIDDING.propoe(new Bid(MARIANE,430.00));
        BIDDING.propoe(new Bid(ARTHUR,440.00));
        BIDDING.propoe(new Bid(MARIANE,450.00));
        BIDDING.propoe(new Bid(ARTHUR,460.00));
        BIDDING.propoe(new Bid(MARIANE,470.00));
        BIDDING.propoe(new Bid(ARTHUR, 480.00));
    }

    //Crie uma nova classe responsável por formatar valores, então crie a classe de teste para a nova classe e aplique o TDD.
    //Ao finalizar a implementação, rode os testes, caso passar da maneira esperada. Adicione na tela e veja se tudo funciona como esperado.
    // Link com commit para facilitar: https://github.com/alura-cursos/tdd-android-parte-2/commit/ccb39f0530dd98c106fe448e595ec92abd5e5358
    //Código completo do professor: https://github.com/alura-cursos/tdd-android-parte-2/archive/desafio.zip
}