package org.ethereum.jsontestsuite;

import org.ethereum.core.BlockHeader;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static org.junit.Assert.assertArrayEquals;

/**
 * @author Mikhail Kalinin
 * @since 03.09.2015
 */
public class GitHubPowTest {

    private static final Logger logger = LoggerFactory.getLogger("TCK-Test");
    private String shacommit = "25912e023e7cf25c33ed6dff078df0c941f2c7d6";

    @Test
    public void runEthashTest() throws IOException {

        String json = JSONReader.loadJSONFromCommit("PoWTests/ethash_tests.json", shacommit);

        EthashTestSuite testSuite = new EthashTestSuite(json);

        for (EthashTestCase testCase : testSuite.getTestCases()) {

            logger.info("Running {}\n", testCase.getName());

            BlockHeader header = testCase.getBlockHeader();

            assertArrayEquals(testCase.getResultBytes(), header.calcPowValue());
        }

    }
}
