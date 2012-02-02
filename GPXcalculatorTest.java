package GPXlibrary.src.edu.upenn.cis350.gpx;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class GPXcalculatorTest {

	GPXtrkpt noPoint,validA,sameValidA,validB,validC,validE,validF,validG,validH,validI,validJ;
	ArrayList<GPXtrkpt> onePointList, nullPointList, validPointListA, validPointListB,validPointListC;
	GPXtrkseg emptySeg, onePointSeg, nullPointSeg, validPointSegA, validPointSegB, validPointSegC;
	
	@Before
	public void setUp() throws Exception {
		noPoint = null;
	    validA = new GPXtrkpt(10,10,new Date());
		sameValidA = new GPXtrkpt(10,10,new Date());
		validB = new GPXtrkpt(10,20,new Date());
		validC = new GPXtrkpt(0,0,new Date());
		validE = new GPXtrkpt(-91,10,new Date());
		validF = new GPXtrkpt(91,10,new Date());
		validG = new GPXtrkpt(10,190,new Date());
		validH = new GPXtrkpt(10,-190,new Date());
		validI = new GPXtrkpt(-91,190,new Date());
		validJ = new GPXtrkpt(20,10,new Date());
		onePointList = new ArrayList<GPXtrkpt>();
		onePointList.add(validA);
		
		nullPointList = new ArrayList<GPXtrkpt>();
		nullPointList.add(validA);
		nullPointList.add(null);
		
		validPointListA = new ArrayList<GPXtrkpt>();
		validPointListA.add(validA);
		validPointListA.add(validB);
		
		validPointListB = new ArrayList<GPXtrkpt>();
		validPointListB.add(validA);
		validPointListB.add(validJ);
		
		validPointListC = new ArrayList<GPXtrkpt>();
		validPointListC.add(validA);
		validPointListC.add(validJ);
		validPointListC.add(validE);
	
		GPXtrkseg emptySeg = new GPXtrkseg(new ArrayList<GPXtrkpt>());
		GPXtrkseg onePointSeg = new GPXtrkseg(onePointList);
		GPXtrkseg nullPointSeg = new GPXtrkseg(nullPointList);
		GPXtrkseg validPointSegA = new GPXtrkseg(validPointListA);
		GPXtrkseg validPointSegB = new GPXtrkseg(validPointListB);
		GPXtrkseg validPointSegC = new GPXtrkseg(validPointListC);
	}

	@Test
	public void testValid(){
		ArrayList<GPXtrkseg> temp = new ArrayList<GPXtrkseg>();
		temp.add(validPointSegA);
		assertTrue(GPXcalculator.calculateDistanceTraveled(new GPXtrk("validA",temp))==10);
		temp.add(validPointSegB);
		assertTrue(GPXcalculator.calculateDistanceTraveled(new GPXtrk("validB",temp))==20);
	}
	public void testNull() {
		assertTrue(GPXcalculator.calculateDistanceTraveled(null)==-1);
	}
	public void testEmptyTrk(){
		assertTrue(GPXcalculator.calculateDistanceTraveled(new GPXtrk("emptyTRK",null))==-1);
		assertTrue(GPXcalculator.calculateDistanceTraveled(new GPXtrk("emptyTRK",new ArrayList<GPXtrkseg>()))==-1);
	}
	public void testNullSeg(){
		ArrayList<GPXtrkseg> temp = new ArrayList<GPXtrkseg>();
		temp.add(validPointSegA);
		assertTrue(GPXcalculator.calculateDistanceTraveled(new GPXtrk("validA",temp))==10);
		temp.add(null);
		assertTrue(GPXcalculator.calculateDistanceTraveled(new GPXtrk("validB",temp))==0);
	}
	public void testEmptySeg(){
		ArrayList<GPXtrkseg> temp = new ArrayList<GPXtrkseg>();
		temp.add(validPointSegA);
		assertTrue(GPXcalculator.calculateDistanceTraveled(new GPXtrk("validA",temp))==10);
		temp.add(emptySeg);
		assertTrue(GPXcalculator.calculateDistanceTraveled(new GPXtrk("validB",temp))==0);
	}
	public void testOnePointSeg(){
		ArrayList<GPXtrkseg> temp = new ArrayList<GPXtrkseg>();
		temp.add(validPointSegA);
		assertTrue(GPXcalculator.calculateDistanceTraveled(new GPXtrk("validA",temp))==10);
		temp.add(onePointSeg);
		assertTrue(GPXcalculator.calculateDistanceTraveled(new GPXtrk("validB",temp))==10);
	}
	public void testNullPointSeg(){
		ArrayList<GPXtrkseg> temp = new ArrayList<GPXtrkseg>();
		temp.add(validPointSegA);
		assertTrue(GPXcalculator.calculateDistanceTraveled(new GPXtrk("validA",temp))==10);
		temp.add(nullPointSeg);
		assertTrue(GPXcalculator.calculateDistanceTraveled(new GPXtrk("validB",temp))==10);
	}
	public void testOverflowPointSeg(){
		ArrayList<GPXtrkseg> temp = new ArrayList<GPXtrkseg>();
		temp.add(validPointSegA);
		assertTrue(GPXcalculator.calculateDistanceTraveled(new GPXtrk("validA",temp))==10);
		temp.add(validPointSegC);
		assertTrue(GPXcalculator.calculateDistanceTraveled(new GPXtrk("validB",temp))==10);
	}
}
