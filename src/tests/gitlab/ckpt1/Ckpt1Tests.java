package tests.gitlab.ckpt1;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        NGramToNextChoicesMapTests.class,
        MoveToFrontListTests.class,
        CircularArrayComparatorTests.class
})

public class Ckpt1Tests {

}