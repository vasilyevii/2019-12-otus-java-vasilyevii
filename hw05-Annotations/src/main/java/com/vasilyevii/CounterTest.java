package com.vasilyevii;

class CounterTest {

    public CounterTest() {
    }

    @Before
    void setUp1() {
        System.out.println("@Before - setUp1");
    }

    @Before
    void setUp2() {
        System.out.println("@Before - setUp2");
    }

    @Test
    void inc() {
        System.out.println("@Test - inc");
        int a = 1 / 0;
    }

    @Test
    void dec() {
        System.out.println("@Test - dec");
    }

    @After
    void tearDown() {
        System.out.println("@After - tearDown");
    }

}