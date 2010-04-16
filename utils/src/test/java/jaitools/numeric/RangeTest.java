/*
 * Copyright 2010 Michael Bedward
 *
 * This file is part of jai-tools.
 *
 * jai-tools is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * jai-tools is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with jai-tools.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package jaitools.numeric;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author Michael Bedward
 */
public class RangeTest {

    @Test
    public void testCreateInterval() {
        System.out.println("   testCreateInterval");

        final int value = 42;

        Range<Integer> r = Range.create(null, false, value, true);
        assertTrue(r.isMinOpen());
        assertTrue(r.isMinNegInf());
        assertNull(r.getMin());

        assertFalse(r.isMaxOpen());
        assertFalse(r.isMaxInf());
        assertNotNull(r.getMax());
        assertEquals(value, r.getMax().intValue());
    }

    @Test
    public void testCreateInfiniteInterval() {
        System.out.println("   testCreateInfiniteInterval");
        Range<Integer> r1 = Range.create(null, true, null, true);
        assertTrue(r1.isMinNegInf());
        assertTrue(r1.isMaxInf());
    }

    @Test
    public void testEquals() {
        System.out.println("   testEquals");
        
        Range<Integer> r = Range.create(-10, true, 10, false);
        Range<Integer> same = Range.create(-10, true, 10, false);
        Range<Integer> different = Range.create(-10, false, 10, true);

        assertTrue(r.equals(same));
        assertFalse(r.equals(different));
    }

    @Test
    public void testInfinitePointNEQInfiniteInterval() {
        System.out.println("   testInfinitePointNEQInfiniteInterval");

        Range<Integer> p = Range.create(null, Range.INF);
        Range<Integer> r = Range.create(null, false, null, false);

        assertFalse(p.equals(r));
        assertFalse(r.equals(p));
    }

    @Test
    public void testFinitePointToString() {
        System.out.println("   testPointToString");

        Range<Integer> r = Range.create(10);
        assertEquals("Range[10]", r.toString());
    }

    @Test
    public void testInfinitePointToString() {
        System.out.println("   testInfinitePointToString");

        Range<Integer> r = Range.create(null, Range.INF);
        assertEquals("Range(Inf)", r.toString());

        r = Range.create(null, Range.NEG_INF);
        assertEquals("Range(-Inf)", r.toString());
    }
    
    @Test
    public void testFiniteIntervalToString() {
        System.out.println("   testFiniteIntervalToString");

        Range<Integer> r = Range.create(-10, true, 10, false);
        assertEquals("Range[-10, 10)", r.toString());
    }

    @Test
    public void testOpenIntervalToString() {
        System.out.println("   testOpenIntervalToString");

        Range<Integer> r = Range.create(null, true, 10, true);
        assertEquals("Range(-Inf, 10]", r.toString());
    }
}