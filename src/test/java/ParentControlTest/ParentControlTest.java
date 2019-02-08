package ParentControlTest;


import ParentControlCode.ParentControlServiceImpl;
import ParentControlCode.TechnicalFailureException;
import ParentControlCode.TitleNotFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParentControlTest {

    private ParentControlServiceImpl parentControlService;

    @Before
    public void setUp(){
        parentControlService = new ParentControlServiceImpl();
    }

    @Test
    public void lowerParentControlLevelReturnTrue() throws Exception{
        Assert.assertTrue(parentControlService.canWatchMovie("18", "3"));
    }

    @Test
    public void higherParentControlLevelReturnFalse() throws Exception{
        Assert.assertFalse(parentControlService.canWatchMovie("U", "3"));
    }

    @Test
    public void equalParentControlLevelReturnTrue() throws Exception{
        Assert.assertTrue(parentControlService.canWatchMovie("18", "5"));
    }

    @Test(expected = TitleNotFoundException.class)
    public void exceptionTitleNotFondThrownWhenInvalidTitle() throws TitleNotFoundException, TechnicalFailureException {
        parentControlService.canWatchMovie("U", "7");
    }

    @Test(expected = TechnicalFailureException.class)
    public void exceptionTechnicalFailureExceptionWhenInvalidLevel() throws TitleNotFoundException, TechnicalFailureException {
        parentControlService.canWatchMovie("UP", "4");
        parentControlService.canWatchMovie(null, null);
    }
}
