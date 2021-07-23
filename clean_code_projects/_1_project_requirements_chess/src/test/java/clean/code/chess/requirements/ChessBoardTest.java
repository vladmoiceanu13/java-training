package clean.code.chess.requirements;

import clean.code.chess.requirements.attributes.PieceColor;
import clean.code.chess.requirements.pieces.Pawn;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ChessBoardTest {

    private ChessBoard testSubject;

    @Before
    public void setUp() {
        testSubject = new ChessBoard();
    }

    @Test
    public void testHas_MaxBoardWidth_of_7() {
        Assert.assertEquals(7, ChessBoard.MAX_BOARD_HEIGHT);
    }

    @Test
    public void testHas_MaxBoardHeight_of_7() {
        Assert.assertEquals(7, ChessBoard.MAX_BOARD_HEIGHT);
    }

    @Test
    public void testIsLegalBoardPosition_True_X_equals_0_Y_equals_0() {
        boolean isValidPosition = ChessBoard.IsLegalBoardPosition(0, 0);
        Assert.assertTrue(isValidPosition);
    }

    @Test
    public void testIsLegalBoardPosition_True_X_equals_5_Y_equals_5() {
        boolean isValidPosition = ChessBoard.IsLegalBoardPosition(5, 5);
        Assert.assertTrue(isValidPosition);
    }

    @Test
    public void testIsLegalBoardPosition_False_X_equals_11_Y_equals_5() {
        boolean isValidPosition = ChessBoard.IsLegalBoardPosition(11, 5);
        Assert.assertFalse(isValidPosition);
    }

    @Test
    public void testIsLegalBoardPosition_False_X_equals_0_Y_equals_9() {
        boolean isValidPosition = ChessBoard.IsLegalBoardPosition(0, 9);
        Assert.assertFalse(isValidPosition);
    }

    @Test
    public void testIsLegalBoardPosition_False_X_equals_11_Y_equals_0() {
        boolean isValidPosition = ChessBoard.IsLegalBoardPosition(11, 0);
        Assert.assertFalse(isValidPosition);
    }

    @Test
    public void testIsLegalBoardPosition_False_For_Negative_Y_Values() {
        boolean isValidPosition = ChessBoard.IsLegalBoardPosition(5, -1);
        Assert.assertFalse(isValidPosition);
    }

    @Test
    public void Avoids_Duplicate_Positioning() {
        Pawn firstPawn = new Pawn(PieceColor.BLACK);
        Pawn secondPawn = new Pawn(PieceColor.BLACK);
        testSubject.Add(firstPawn, 6, 3, PieceColor.BLACK);
        testSubject.Add(secondPawn, 6, 3, PieceColor.BLACK);
        Assert.assertEquals(6, firstPawn.getXCoordinate());
        Assert.assertEquals(3, firstPawn.getYCoordinate());
        Assert.assertEquals(-1, secondPawn.getXCoordinate());
        Assert.assertEquals(-1, secondPawn.getYCoordinate());
    }

    @Test
    public void testLimits_The_Number_Of_Pawns() {
        for (int i = 0; i < 10; i++) {
            Pawn pawn = new Pawn(PieceColor.BLACK);
            int row = i / ChessBoard.MAX_BOARD_WIDTH;
            testSubject.Add(pawn, 6 + row, i % ChessBoard.MAX_BOARD_WIDTH, PieceColor.BLACK);
            if (row < 1) {
                Assert.assertEquals(6 + row, pawn.getXCoordinate());
                Assert.assertEquals(i % ChessBoard.MAX_BOARD_WIDTH, pawn.getYCoordinate());
            } else {
                Assert.assertEquals(-1, pawn.getXCoordinate());
                Assert.assertEquals(-1, pawn.getYCoordinate());
            }
        }
    }
}