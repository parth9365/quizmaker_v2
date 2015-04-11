package com.quiz.commons;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.TimeZone;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;



public class CommonFuncs{
	
	public static  Properties configProp=null;
	public static  Properties clinicProp=null;
	public static String baseDir=ProjectConfig.get("projectbase");
	public static String docDir=ProjectConfig.get("documentbase");
	public static String auditTrailDir=ProjectConfig.get("auditTrailPath");
	
	// public static String baseDir="";
	public static int noOfCharsPerLine=65;
	public static String separator =""+(char)231;
	/*  public static String uploadFormDirPath="d:/projects/emrs3/tempHTML/uploadfiles";*/
	public static DateFormat dateformat=new SimpleDateFormat("MMM dd, yyyy hh:mm:ss a z"); 

//lokesh.insert.begin 16-july-2012
		public static String[] clinicSearchOrderType={"Clinic Name", "City", "Clinic Code"};
//lokesh.insert.end 16-july-2012
	public static String[] NormalAbnormalExamined={"Normal","Abnormal","Not Examined"};

	//  public static String[] state={"Alabama","Alaska","Arkansas","Am. Samoa","Arizona","California","Colorado","Connecticut","Dist. of Columbia","Delaware","Florida","Georgia","Guam","Hawaii","Iowa","Idaho","Illinois","Indiana","Kansas","Kentucky","Louisiana","Massachusetts","Maryland","Maine","Michigan","Minnesota","Missouri","Mississippi","Montana","North Carolina","North Dakota","Nebraska","New Hampshire","New Jersey","New Mexico","Nevada","New York","Ohio","Oklahoma","Oregon","Pennsylvania","Puerto Rico","Rhode Island","South Carolina","South Dakota","Tennessee","Texas","Utah","Virginia","Virgin Islands","Vermont","Washington","Wisconsin","West Virginia","Wyoming","APO - AA","APO - AE","APO - AP"};
	//  public static String[] stateAbbr={"AL","AK","AR","AS","AZ","CA","CO","CT","DC","DE","FL","GA","GU","HI","IA","ID","IL","IN","KS","KY","LA","MA","MD","ME","MI","MN","MO","MS","MT","NC","ND","NE","NH","NJ","NM","NV","NY","OH","OK","OR","PA","PR","RI","SC","SD","TN","TX","UT","VA","VI","VT","WA","WI","WV","WY","AA","AE","AP"};
	public static String[] state={};
	public static String[] stateAbbr={};
	public static String[] country={"USA","Canada","Other"};
	public static String[] gender={"Male","Female"};
	public static String[] relation={"Parent","Spouse","Husband","Wife","Ex-Spouse","Father","Mother","Sibling","Grandparent","Guardian","Attorney","Employer","Adjuster","NCM","Other"};
	public static String[] workStatus={"Full Time","Part Time","Unemployed","Retired","Student/Child","Self Employed","Not able to work","Unknown","Military","Caregiver","Homemaker","Other"};
	public static String[] studentStatus={"Not a Student", "Full Time","Part Time"};
	public static String[] maritalStatus={"Single","Married","Divorced","Legally Separated","Spouse Deceased","Partnered"};
	public static String[] alcoholUse={"Never","Rarely","Moderate","Daily"};
	public static String[] tobaccoUse={"Never","Stopped","Daily"};
	public static String[] caffeineConsumption={"Never","Daily"};
	public static String[] clearBloodyCloudy={"Clear","Bloody","Cloudy"};
	public static String[] type={"Consultation","Prescription","Examination","Accident Examination","Subsequent Visit","Scan Report","Report","Other"};
	public static String[] status={"Pending","Under Correction","Under Review","Signed"	};
	public static String[] doctorStatus={"Enabled","Disabled","New"};
	public static String[] typeOfInsurance={"PPO","HMO","Worker's Group","Blue Cross/Shield","FECA","Group","Champs VA","Tricare/Champus","Medicaid","Medicare","Other"};
	
	public static String[] zeroToTen={"0","1","2","3","4","5","6","7","8","9","10"};
	public static String[] plusMinus={"+","-"};
	public static String[] flow={"Good","Fair","Poor"};
	public static String[] ph={"5.0","5.5","6.0","6.5","7.0","7.5","8.0","8.5"};
	public static String[] spGrav={"1.000","1.010","1.015","1.020","1.025","1.030","1.035","1.040","1.045","1.050"};
 	public static String[] oneToFour={"1+","2+","3+","4+"};
	public static String[] normalAbnormal={"Normal","Abnormal"};
	public static String[] yesNo={"Yes","No"};
	public static String[] onezero={"1","0"}; //added on 200509 by siddharth
	public static String[] noYes={"No","Yes"};
	public static String[] returnToOfficeOptions={"Days","Month","Years"};
	public static String[] company={"Blue Cross Blue Shield of MA","Carelink","CHC of Delaware","CHC of Georgia","CHC of Iowa","CHC of Kansas City","CHC of Kansas","CHC of Louisiana","CHC of Nebraska","CHC of the Carolinas","Group Health Incorporated","Group Health Plan","Health America/Health Assurance","HIP of Greater New York","Humana","Medical Mutual of Ohio","Southern Health Services","WellPath","Other"};
	public static String[] hintQuestion={"what is your pet name"};
	public static String[] autoEligibility={"1","0"};
	public static String[] insuranceType={"Primary","Secondary","Tertiary"};
	public static String[] reportStatus={"Final","Preliminary","Corrected"};

	public static String[] smokerStatus={"Current every day smoker","Current some day smoker","Former smoker","Never smoked","Smoker, current status unknown","Unknown if ever smoker"};
	public static String[] patientReminder={"No Reminder","By E-mail","By Mail","By Phone","By SMS"};

   	public static String[] department={"Cardiology","Chiropractor","Dermatology","Endocrinology","Gastroenterology","General Surgery","Geriatric Medicine","Gynecology","Hematology","Medical Genetics","Neonatal","Nephrology","Neurology","Oncology","Ophthalmology","Orthopaedics","Otolaryngology","Pain Management","Pathology","Pediatrics","Plastic Surgery","Psychiatry","Pulmonary Disease","Pulmonology","Radiology","Urology","Other"};

	public static String[] jobType={"Full Time","Part Time","Consultant","External","Administrator"};
	public static String[] city={"New York","White Plains","Yonkers","Scarsdale","Tarrytown"};
	public static String[] primarySpeciality={"Adjuster","Allergy & Immunology","Anesthesiology","Cardiology","Cardiovascular Disease","Chiropractor","Colon & Rectal Surgery","Dentistry","Dermatology","Emergency Medicine","Endocrinology","Facial Plastic Surgery","Family Practice","Gastroenterology","General Practitioner","General Surgery","Geriatric Medicine","Gynecologic Oncology","Hematology","Infectious Disease","Internal Medicine","Internal Medicine - Cardiovascular Disease","Internal Medicine - Infectious Disease","Interventional Pain Medicine","Maternal & Fetal Medicine","Medical Genetics","Neonatal-Perinatal Medicine","Nephrology","Neurological Surgery", "Neurology","Nuclear Mediciogy","Nursing","Nutritionist","Obstetrics & Gynecology","Occupational Medicine","Oncology","Ophthalmology","Oral Surgery","Orthopedics","Orthopaedic Surgery","Otolaryngology","Pain Management","Pathology","Pediatric Allergy & Immunology","Pediatric Cardiology","Pediatric Critical Care Medicine","Pediatric Emergency Medicine","Pediatric Endocrinology","Pediatric Gastroenterology","Pediatric Hematology-Oncology","Pediatric Infectious Disease","Pediatric Nephrology","Pediatric Pulmonary","Pediatric Rheumatology","Pediatric Sports Medicine","Pediatric Surgery","Pediatrics","Physical Medicine & Rehabilitation","Plastic Surgery","Podiatry","Preventive Medicine","Psychiatry","Psychology","Pulmonology","Pulmonary Disease","Radiation Oncology","Radiology","Renal Transplant Division","Rheumatology","Sports Medicine","Specialist","Surgery","Thoracic Surgery","Urgent Care","Urology","Vascular Surgery","Other","Attorney","Administrator","Billing & Claiming","Wound Care","Clinical Cardiac Electrophysiology"};


	public static String[] role={"Admin","Doctor","DoctorAdmin","ExternalDoctor","Transcriptionist","Assistant","Nurse","Sales","BillingCompany","BillingSupervisor","FrontDesk","Security Administrator","DoctorAdmin-Administrator"};
	public static String[] roleExternal={"Doctor","DoctorAdmin","Transcriptionist","Assistant","Nurse","DoctorAdmin-Administrator"};
	public static String[] timeSpent={"20-30 min","45-50 mins","60-90 mins"};
	public static String months[]={"JAN","FEB","MAR","APR","MAY","JUN","JUL","AUG","SEP","OCT","NOV","DEC"};

	public static String[] vitalSigns={"Temperature","BP","Weight","Pulse","ResponseRate"};

	public static String transcriptionTypes[]={"PatientVisit","Prescription","LabRequisition","Others"};
	public static String billingType[]={"Per Month","Per Page","Per Line"};
	public static String billingCycle[]={"Monthly","Fortnightly"};
	public static String prefix[]={"Dr.","Mr.","Ms.","Mrs."};
	public static String homePage[]={"AppointmentController?action=TodayList","AppointmentController?action=View","AppointmentController?action=View&display=DefaultSiteView&show=addAppointment","TranscriptionController?action=List&status=Under%20Review","PatientController?action=SearchForm"};
	public static String homePageDisplay[]={"Today's List","My Schedule","Site Schedule","Transcriptions","Patients"};

	public static String bloodType[]={"A+","A-","B+","B-","AB+","AB-","O+","O-"};
	public static String race[]={"White","Black","Hispanic","American Indian","Asian","Other"};
	public static String paymentStatus[]={"Processing","Paid"};
	public static String clinicSiteType[]={"Clinic","External"};
	public static String otherDiscountType[]={"Percentage","Amount"};
    public static final String sqlServerDateFormat="yyyy-MM-dd HH:mm:ss.SSS";

	public static String[] appointmentTypes={"Appointment","New Office Visit","Established Office Visits","Office Consultation Services","Inpatient Consultation Services","Emergency Services","Admission H&Ps","Hospital Progress Notes","Observation","Observation and D/C","In-Patient Round","Site Visit","Personal","Break","Worker's Comp","Follow-Up","Initial/New Patient","Narrative","Post-Op","Schedule Loss of Use","X-Ray","Walk In"};
	public static String[] weekDaysText= {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
	public static String[] weekDaysNum = {"1","2","3","4","5","6","7"};
	public static int[] weekDaysBit={0x1,0x2,0x4,0x8,0x10,0x20,0x40};
	public static String[] weekDaysChar= {"S", "M", "T", "W", "T", "F", "S"};

	public static int[] weekNumBit={0x1,0x2,0x4,0x8};
	public static String[] weekNumText={"1st","2nd","3rd","4th"};
	public static String[] yearlyRepeatSpecs={"Quaterly","HalfYearly","Yearly"};
	public static int[] yearlyRepeatNum={0x1,0x2,0x4};

	public static String[] outgoingRefStatusValue={"","VIncoming","N","RUOutgoing","E"};
	public static String[] outgoingRefStatusdisplayNames={"All","Viewed","New","Response Updated","Expired"};
	public static String[] incomingRefStatusValue={"","VOutgoing","P","RUIncoming","E"};
	public static String[] incomingRefStatusdisplayNames={"All","Viewed","Pending","Response Updated","Expired"};
	public static String[] superBillStatus={"Pending","Under Process","Reviewed","Completed","Disabled", "Hold", "Billed"};
	public static String[] contactTypes={"Face to face meeting","Send brochure","Make a telephone call","Demo"};
	public static String[] syncType={"PULL","PUSH","SYNC"};
	public static String[] preferredPhoneNo={"CellPhone","HomePhone","WorkPhone"};
	public static String[] carrierType={"Other","Medicare","Medicaid","Tricare/Champus","ChampsVA","Group","FECA","Blue Cross/Shield","Worker's Comp","Employer","HMO","PPO","TPA","Participating","Commercial","Transaction","Guarantor","Attorney","SelfPay","No Fault","Federal Employees Program"};
	public static String[][] carrierCode={{"","Other"},{"MCARE","Medicare"},{"BCBS","Blue Cross/Shield"},{"COMM","Commercial"},{"MCAID","Medicaid"},{"CHAMP","Tricare/Champus"}};

	public static String[] patientReminderStatus = {"Confirmed Appointment","Cancelled Appointment","Reschedule Appointment","Late Appointment","Message Delivered","Number Busy","Not Reachable"};
	public static String[] phoneOrderDisplay={"Cell Only","Home Only","Work Only","Cell->Home","Cell->Work","Home->Cell","Home->Work","Work->Cell","Work->Home","Cell->Home->Work","Cell->Work->Home","Home->Cell->Work","Home->Work->Cell","Work->Cell->Home","Work->Home->Cell"};
	public static String[] phoneOrderValue={"C","H","W","CH","CW","HC","HW","WC","WH","CHW","CWH","HCW","HWC","WCH","WHC"};
	public static String[] messageOptions = {"C","R","X","CR","CX","RX","CRX"};

	public static String[] treatmentStatusValues={"0","1","2","4","8","16","32","64","128","256","512","1024"};
	public static String[] treatmentStatusNames ={"","Pending Insurance Certification","Patient Availability","Pending (Other)","In Treatment","Discharged (Healed)","Discharged (Amputated)","Discharged (Treatment Aborted)","Discharged (Never Treated)","Discharged (Healing)","Discharged (Died)","Discharged (No Improvement)"};

	public static String[] mode = {"Cash","Check","CCard","Electronic","Other"};

	public static String[] gcodeReasonDesc= {"Patient Requested", "Pharmacy Requested", "State Federal Law"};
	public static String[] gcodeReasonValue= {"1", "2", "3"};
	public static String[] reportGenerationValue= {"Daily","Weekly", "Monthly", "Yearly"};
	public static String patientType[]={"Deceased","Transferred","Moved","Seen as consultation only","Other"};
	public static String advanceDirective[]={"Living Will","Durable Power of Attorney","Do not Resuscitate","Other"};
	public static String[] woundDressing = {"Absorbent (cotton gauze or non-woven)","Alginates","Allevyn","Apligraf","Aquasorb","Becalpermin","ClearSite","Comfeel","Compression Bandages",
	           "Coversite","Dermagraft","Dry Sterile Dressing","DuoDerm","ElastoGel / Elasto-Gel","Flexan / Flexzan","Granufoam","Human Fibroblast-Derived Dermal Substitute",
	           "Hydrocolloid wafers","Hydrogels","IntraSite","Kalostat / Kaltostat","Lyofoam","Mesalt","Moist Wound Dressing w/o negative pressure","Negative Pressure dressing (NPWT)",
	           "Non Adherent Dressing","Nuderm","Nu-Derm","OpSite","Organogenesis","Polyurethane foams","Purified human-platelet derived growth factor","Restore","RW Johnson",
	           "Santyl","Semi-synthetic human skin","Silver (AgNO3) Dressings","Silver Foam","Smith & Nephew","Sorbsan","Tegabsorb / Tegasorb","Tegaderm","Thin films","Wet to dry"};

    public static int ATTRIB_ELIGIBILITY  	= 0x1;
	public static int ATTRIB_VITALSIGNS  	= 0x2;
	public static int ATTRIB_TRANSCRIPTION 	= 0x4;
	public static int ATTRIB_VISITCLOSURE  	= 0x8;
	public static int ATTRIB_PRESCRIPTION  	= 0x10;
	public static int ATTRIB_CHARGECAPTURE 	= 0x20;
	public static int ATTRIB_COLLECTCOPAY  	= 0x40;
	
	public static int ATTRIB_LABORDER  			= 0x80;
    public static int ATTRIB_HPI  				= 0x100;
	public static int ATTRIB_ROS  				= 0x200;
	public static int ATTRIB_PHYEXAM  			= 0x400;
	public static int ATTRIB_ASSMPLAN  			= 0x800;
	public static int ATTRIB_ELIGIBLE  			= 0x1000;
	public static int ATTRIB_NOTELIGIBLE 		= 0x2000;
	public static int ATTRIB_TRP 				= 0x4000;

	public static int ATTRIB_CASERPT 			= 0x8000;

	/***Added by Kishore **/
	public static int ATTRIB_HPI_NOTE			=0x10000;
	public static int ATTRIB_ROS_NOTE			=0x20000;
	public static int ATTRIB_TRP_NOTE			=0x40000;
	public static int ATTRIB_APPTADDITIONAL		=0x80000;

   	/**Added By Shailendra ***/
    public static int ATTRIB_ADDITIONALBILLING_ISSUE			=0x100000;
    public static int ATTRIB_PATIENTPAYMENT						=0x200000;

	public static int ATTRIB_DEMOG								= 0x200;
	public static int ATTRIB_HHF_NOTE							= 0x400;
	public static int ATTRIB_NKDA								= 0x800;//No known drug allergy
	public static int ATTRIB_NKA								= 0x4000;//No known allergy

	public static int ATTRIB_ADVANCEDIRECTIVE					= 0x1000;//

	public static int ATTRIB_FMH								= 0x1;
	public static int ATTRIB_SOH								= 0x2;
	public static int ATTRIB_PMH								= 0x4;
	public static int ATTRIB_ALLERGY							= 0x8;
	public static int ATTRIB_INSURANCE							= 0x10;
	public static int ATTRIB_SDOCUMENT							= 0x20;
	public static int ATTRIB_ANNOTATION							= 0x40;

	/***Added by Kishore **/
	public static int ATTRIB_FMH_NOTE							= 0x40;
	public static int ATTRIB_SOH_NOTE							= 0x80;
	public static int ATTRIB_PMH_NOTE							= 0x100;

	//Attributes for Clinic
	public static int ATTRIB_BILLING							= 0x1;
	public static int ATTRIB_MSBILLING							= 0x2;
	public static int ATTRIB_EDIBILLING							= 0x4;
	public static int ATTRIB_PRODUCTION							= 0x8;

	//Attributes for Patient Alert History
	public static int ATTRIB_APPT								= 0x1;
	public static int ATTRIB_DISC								= 0x2;

 	public static int ATTRIB_RENDERINGPROVIDER  				= 0x1;
	public static int ATTRIB_GROUPNUMBER  						= 0x2;
	public static int ATTRIB_GROUPNUMBERIR 						= 0x4;
	public static int ATTRIB_GROUPNAME	 						= 0x8;
	public static int ATTRIB_INSUREDIDNUMBER 					= 0x10;
	public static int ATTRIB_HCPCSPROCEDURECODE  				= 0x20;
	public static int ATTRIB_PATIENTSIGNATURESOURCE  			= 0x40;
	public static int ATTRIB_PAYERCLAIMOFFICENUMBER 			= 0x80;
	public static int ATTRIB_PRIMARYDIAGNOSISCODE  				= 0x100;
	public static int ATTRIB_BILLINGPROVIDERNUMBER  			= 0x200;
    public static int ATTRIB_RENDERINGPROVIDERNUMBER  			= 0x400;
	public static int ATTRIB_REFERRINGPROVIDER  				= 0x800;
	public static int ATTRIB_REMARKS  							= 0x1000;
	public static int ATTRIB_SOURCEOFPAYMENT  					= 0x2000;
	public static int ATTRIB_TYPEOFSERVICE  					= 0x4000;
	public static int ATTRIB_UNITSORMINUTES 					= 0x8000;
	public static int ATTRIB_RENDERINGPROVIDERNETWORKID 		= 0x10000;
	public static int ATTRIB_BILLINGPROVIDERCOMMERCIALNUMBER	= 0x20000;
	public static int ATTRIB_PAYERNAME							= 0x40000;
	public static int ATTRIB_PRIORAUTHORIZATIONNUMBER			= 0x80000;
	public static int ATTRIB_PRIORAUTHORIZATIONNUMBERIR			= 0x100000;

	public static int ATTRIB_DRUGELIGIBILITY_APPT				= 0x800000;
	
	public static int ATTRIB_NEW_VITALSIGN_APPT					= 0x1000000;
	public static int ATTRIB_NEW_ROS_APPT						= 0x2000000;
	public static int ATTRIB_NEW_HPI_APPT						= 0x4000000;
	public static int ATTRIB_NEW_PHYEXM_APPT					= 0x8000000;
	public static int ATTRIB_IS_NEW_TEMPLATE_APPT				= 0x10000000;
	public static int ATTRIB_NEW_TRP_APPT 						= 0x20000000;
	public static int ATTRIB_LAB 								= 0x40000000;
	
	public static int ATTRIB_NEW_FMH_PT							= 0x1000000;
	public static int ATTRIB_NEW_PMH_PT							= 0x2000000;
	public static int ATTRIB_NEW_SOH_PT							= 0x4000000;
	public static int ATTRIB_IS_NEW_TEMPLATE_PT					= 0x8000000;
	
	public static int ATTRIB_CCD								= 0x10000000;
	public static int ATTRIB_REFERRAL							= 0x20000000;
	public static int ATTRIB_CONFIDENTIAL						= 0x40000000;
	
	public static int ATTRIB_CaseManagement_PT					= 0x80000000;    
	
	//added by rajnikant for oncology 
	public static int ATTRIB_CHEMOORDERCASE	=	0x400000;
    public static int ATTRIB_CHEMOADMINISTRATIONCASE	=0x800000;
    public static int ATTRIB_CHEMOORDER			= 0x400;
	public static int ATTRIB_CHEMOADMINISTRATION= 0x800;
	
    /* 
	static{
		try{
			//populateConfigProp();
			//PopulateUSAreaCodeState();
		}catch(Exception e){
			e.printStackTrace(System.out);
		}
	}*/

	public static  String getTreatmentStatusName(String tsValue){
		for(int k=0; k<treatmentStatusValues.length; k++)
		{
			if(treatmentStatusValues[k].equals(tsValue)) return treatmentStatusNames[k];
		}
		return "";
	}
	
	
	public static  void populateConfigProp() throws FileNotFoundException,IOException {

		FileInputStream fis=new FileInputStream(ProjectConfig.get("projectbase") + "/emrs3.config");
		configProp =new Properties();
		configProp.load(fis);
		fis.close();
	}
 	
	public static String getClinicUploadPath(String clinicCode){
		return ProjectConfig.get("projectbase") + "/" + clinicCode;
	}

	public static String getTranscriptionUploadPath(String transcriptionId){
			return ProjectConfig.get("projectbase") + "/MTFTP/" + transcriptionId;
	}
 
	
	public static String[] tokenize(String list){

		StringTokenizer st = new StringTokenizer(list, ";|,");
		String[] array = new String[st.countTokens()];
		int i = 0;
		while (st.hasMoreTokens()) {
			array[i] = st.nextToken();
			i++;
		}
		
		return array;
	}

	public static String[] tokenize(String list,String sep){

		StringTokenizer st = new StringTokenizer(list, sep);
		String[] array = new String[st.countTokens()];
		int i = 0;
		while (st.hasMoreTokens()) {
         array[i] = st.nextToken();
			i++;
		}
		return array;
	}

	public static String[] tokenizeWithComma(String list){

		StringTokenizer st = new StringTokenizer(list, ",");
		String[] array = new String[st.countTokens()];
		int i = 0;
		
		while (st.hasMoreTokens()) {
          array[i] = st.nextToken();
		  i++;
		}
		return array;
	}

	public static String convertArray2String(String[] listArray){

		String list="";
		if(listArray==null) return list;
		
		for(int i=0 ; i < listArray.length; i++){
			if(i==0)
				list += listArray[i];
			else
				list += ","+listArray[i];
		}
		return list;
	}

	public static String listWithQuotes(String list){

		//StringTokenizer st = new StringTokenizer(list, ",");
		String listWithQuotesString="";
		if (!list.equals(""))
		listWithQuotesString="'"+list.replaceAll(",", "','")+"'";
		//int i = 0;
		/*while (st.hasMoreTokens()) {
		if(i==st.countTokens() )
	//	listWithQuotesString += "'"+st.nextToken()+"'";
	//	else
         listWithQuotesString += "'"+st.nextToken()+"',";
			i++;
      }*/
		return listWithQuotesString;
	}


	public static String listStringWithQuotes(String list){

		StringTokenizer st = new StringTokenizer(list, ",");
		String listWithQuotesString="";
		int i = 0;
		
		while (st.hasMoreTokens()) {
			if(i==0)
				listWithQuotesString += "'"+st.nextToken()+"'";
			else
				listWithQuotesString += ",'"+st.nextToken()+"'";
			i++;
		}
		
		return listWithQuotesString;
	}
	
	
	public static String getEmailHeader() {
		return "<HTML><BODY>";
	}

	public static String getEmailFooter(){
		return "</BODY></HTML>";
	}

	public  static String convert2USDate(String date) {
		if(date==null || date.equals(""))return "";
		if(date.indexOf("-")==-1)
		  return date;
	 	int d1 = date.indexOf("-");
		int d2 = date.indexOf("-",d1+1);
		int d3 = date.indexOf(" ");
	
		try{
			//return Integer.parseInt(date.substring(d1+1,d2)) + "/" + Integer.parseInt(date.substring(d2+1,d3)) + "/" + date.substring(0,d1);
			return date.substring(d1+1,d2) + "/" + date.substring(d2+1,d3) + "/" + date.substring(0,d1);
		}catch(Exception e) {
			e.printStackTrace(System.out);
			return date;
		}
	}
	
	/**
	 * 
	 * yyyy-MM-dd --> MM/dd/yyyy
	 * 
	 * @param date in yyyy-MM-dd format
	 * @return date in MM/dd/yyyy
	 */
	public static String convert2USDateFrom120(String date) {
		if(date==null || date.equals(""))return "";
		if(date.indexOf("-")==-1)
			return date;
		String yyyy = date.substring(0,4);
		String MM = date.substring(5,7);
		String dd = date.substring(8,10);
		return MM+"/"+dd+"/"+yyyy;
	}

	public  static String convert2USDateForDOB(String date) {
		if(date==null || date.equals(""))return "";
		if(date.indexOf("-")==-1)
			return date;
	 	int d1 = date.indexOf("-");
		int d2 = date.indexOf("-",d1+1);
		int d3 = date.indexOf(" ");

		try{
			return date.substring(d1+1,d2) + "/" + date.substring(d2+1,d3) + "/" + date.substring(0,d1);
		}catch(Exception e) {
			e.printStackTrace(System.out);
			return date;
		}
	}

//---
	public  static String convert2USDateWithHourMinute(String date) {
		if(date==null || date.equals(""))return "";
		if(date.indexOf("-")==-1)
		  return date;
	 	int d1 = date.indexOf("-");
		int d2 = date.indexOf("-",d1+1);
		int d3 = date.indexOf(" ");
		int d4 = date.lastIndexOf(":");
		
		try{
			return Integer.parseInt(date.substring(d1+1,d2)) + "/" + Integer.parseInt(date.substring(d2+1,d3)) + "/" + date.substring(0,d1)+" "+date.substring(d3,d4);
		}catch(Exception e){
			e.printStackTrace(System.out);
			return date;
		}
	}
//----

	public  static String convert2ESTFormat(String date) {
		if(date==null || date.equals(""))return "";
		try{
			return date.substring(0,3)+"/"+date.substring(4,5)+"/"+date.substring(9,11)+" "+date.substring(11);
		}catch(Exception e){
			e.printStackTrace(System.out);
			return date;
		}
	}

	public static Date getUtilDateTime(String date) {
		int index1=date.indexOf("/");
		int index2=date.lastIndexOf("/");
		int index3=date.indexOf(":");
		int index4=date.indexOf("AM");
		int index5=date.indexOf("PM");
		//int mm=Integer.parseInt(date.substring(0,index1).trim())-1;
		int mm=Integer.parseInt(date.substring(0,index1).trim())-1;
		int dd=Integer.parseInt(date.substring(index1+1,index2).trim());
		int yy=Integer.parseInt(date.substring(index2+3,index2+5).trim())+100;
		int hh=Integer.parseInt(date.substring(index3-2,index3).trim());
		int min=0;
		
		if (index5!=-1){
			if(hh!=12) 
				hh+=12;
		 	
			min=Integer.parseInt(date.substring(index3+1,index5).trim());
	 	}else
	 		min=Integer.parseInt(date.substring(index3+1,index4).trim());
		return new java.util.Date(yy,mm,dd,hh,min);
	}



	public static String printSelectOptions(String[] valueArray, String selectedValue){
		StringBuffer output = new StringBuffer();
	
		for(int i=0; i< valueArray.length;i++){
			output.append("\n<option value=\"");
			output.append(valueArray[i]);
			
			if(valueArray[i].equalsIgnoreCase(selectedValue))
				output.append("\" SELECTED>");
			else
				output.append("\">");
		 
			output.append(valueArray[i]);
		}
		
		return output.toString();
	}

	public static String printSelectOptions(String[] valueArray,String[] displayArray, String selectedValue){
		return 	printSelectOptions(valueArray, displayArray, null, selectedValue);
	}

	
	public static String printSelectOptions(String[] valueArray,String[] displayArray,String[] idArray, String selectedValue){
		
		StringBuffer output = new StringBuffer();
	
		for(int i=0; i< valueArray.length;i++){
			if(idArray==null) 
				output.append("\n<option value=\"");
			else  
				output.append("\n<option id=\""+idArray[i]+"\" value=\"");
		 
			output.append(valueArray[i]);
		 
			if(valueArray[i].equalsIgnoreCase(selectedValue))
				output.append("\" SELECTED>");
			else
				output.append("\">");
		 
			output.append(displayArray[i]);
		}
	
		return output.toString();
	}

	public static String printRadios(String[] valueArray, String radioName, String selectedValue, boolean needBR){
   
		StringBuffer output = new StringBuffer();
	
		for(int i=0; i< valueArray.length;i++){
			output.append("\n<input type=radio name=");
			output.append(radioName);
			output.append(" value=\"");
			output.append(valueArray[i]);
		 
			if(valueArray[i].equalsIgnoreCase(selectedValue))
				output.append("\" CHECKED");
			else
				output.append("\"");

		 
			output.append(" style='border:0px;' >");
			output.append(valueArray[i]);

			if(needBR && i<valueArray.length-1) 
				output.append("<BR>");
		}
		return output.toString();
	}

	//added on 200509 by siddharth
  
	public static String printRadios10(String[] valueArray, String radioName, String selectedValue, boolean needBR){
		
		StringBuffer output = new StringBuffer();
  	
		for(int i=0; i< valueArray.length;i++){
			output.append("\n<input type=radio name=");
			output.append(radioName);
			output.append(" value=\"");
			output.append(valueArray[i]);
  		 
			if(valueArray[i].equalsIgnoreCase(selectedValue))
				output.append("\" CHECKED");
			else
				output.append("\"");
  		 
			output.append(" style='border:0px;' >");
			output.append(getYesNo(valueArray[i]));
  		 
			if(needBR && i<valueArray.length-1) 
				output.append("<BR>");
  	
		}
		return output.toString();
	}
//end 200509


	public static String printRadios(int[] valueArray, String radioName, String[] nameArray,String selectedValue, boolean needBR){
		StringBuffer output = new StringBuffer();
  	
		for(int i=0; i< valueArray.length;i++){
			output.append("\n<input type=radio name=");
			output.append(radioName);
			output.append(" value=\"");
			output.append(valueArray[i]);
  		
			if((""+valueArray[i]).equalsIgnoreCase(selectedValue))
				output.append("\" CHECKED");
			else
				output.append("\"");

			output.append(" style='border:0px;' >");
	  		output.append(nameArray[i]);
  		 
	  		if(needBR && i<valueArray.length-1) 
	  			output.append("<BR>");
		}
  	
		return output.toString();
    }

	public static String setChecked(String selectedValue){
		if(selectedValue.equals("1")) 
			return "CHECKED";
		else return "";
	}
	

	public static String getYesNo(String selectedValue){
		if(selectedValue.equals("1")) 
			return "Yes";
		else 
			return "No";
	}

	public static String setBitWiseChecked(int value,String checkBoxValue){
		if(/*(value && checkBoxValue).equals(checkBoxValue)*/false) return "CHECKED";
		else return "";
	}

	public static boolean isMozilla(HttpServletRequest req){
		if(req.getParameter("pda")!=null) 
			return false;
		
		if(req.getHeader("user-agent").indexOf("Mozilla")!=-1) 
			return true;
		else 
			return false;
	}

	public static String replaceBR(String str){ 	
		int i=0 ;
		StringBuffer sb=new StringBuffer(str);
 	
		while(str.indexOf("<br>")!=-1 || str.indexOf("<BR>")!=-1){
			if(str.indexOf("<br>")!=-1)
				i=str.indexOf("<br>");
			else 
				i=str.indexOf("<BR>");
			
			sb.replace(i,i+4,"\n");
			str=sb.toString();
		}

		return str;
	}


	public static String replaceEnterWithBlank(String str){ 			
		StringBuffer stringBuffer = new StringBuffer();
		
		for(int i=0;i<str.length();i++){
			char c1 = str.charAt(i);
			int c2 = (int)c1;
			
			switch(c2){

				case '\n':
					stringBuffer.append("");
					break;
				case '\r':
					break;

				case ' ':
					stringBuffer.append("");
					break;
				default:
					stringBuffer.append(c1);
					break;
			}
		}
		
		return stringBuffer.toString();
	}


	public static String convertNullToBlank(String s){

		if(s==null || s.equals(""))
			return "";
		else
			return s;
	}

	public static String dbStringValue(String s){
		if(s==null || s.equals(""))
			return null;
		else
			return "'"+s+"'";
	}
	

	public static String dbNumValue(String s){

		if(s.equals(""))
			return null;
		else
			return s;
	}

	
	public static String getDateOfBirthMonths(){
			
		String month="";
		Calendar  calendar =Calendar.getInstance();
		
		for( int i=0 ; i<= calendar.get(Calendar.MONTH); i++){
			month += " <OPTION VALUE="+(i+1)+">"+months[i] +"</OPTION>";
		}
		return month;
	}


	public static String getMonths(){
		String month="";
		for( int i=0 ; i< months.length; i++){
			month += " <OPTION VALUE="+(i+1)+">"+months[i]+"</OPTION>";
		}
		
		return month;
	}

	
	public static String getMonthsWithBlank(){
		String month="";
		month += " <OPTION VALUE= \"\">Month</OPTION>";
		
		for( int i=0 ; i< months.length; i++){
			month += " <OPTION VALUE="+(i+1)+">"+months[i] +"</OPTION>";
		}
		return month;
	}

	//shreyans added this function
	
	public static String getCurrentDateTimeInTimeZoneFull(HttpSession session)
	{
		String timeZone=(String)session.getValue("timeZone");
		String tz=timeZone.replace('(',' ').replace(')',' ');
		if(timeZone.equals("(CST)")) timeZone="US/Central";
		else if(timeZone.equals("(MST)")) timeZone="US/Mountain";
		else if(timeZone.equals("(PST)")) timeZone="US/Pacific";
		else timeZone="US/Eastern";

		Calendar cDate =new GregorianCalendar(TimeZone.getTimeZone(timeZone));
//		String currentDate=((cDate.get(Calendar.MONTH)+1)<10?"0":"")+(cDate.get(Calendar.MONTH)+1)+"/"+(cDate.get(Calendar.DAY_OF_MONTH)<10?"0":"")+cDate.get(Calendar.DAY_OF_MONTH)+"/"+cDate.get(Calendar.YEAR)+" "+((cDate.get(Calendar.HOUR)<10)?"0":"")+((cDate.get(Calendar.HOUR)==0)?12:cDate.get(Calendar.HOUR))+":"+((cDate.get(Calendar.MINUTE)<10)?"0":"")+cDate.get(Calendar.MINUTE)+":"+((cDate.get(Calendar.SECOND)<10)?"0":"")+cDate.get(Calendar.SECOND)+" "+ ((cDate.get(Calendar.AM_PM)==0)?"AM":"PM")+" "+tz;
		String currentDate=((cDate.get(Calendar.MONTH)+1)<10?"0":"")+(cDate.get(Calendar.MONTH)+1)+"/"+(cDate.get(Calendar.DAY_OF_MONTH)<10?"0":"")+cDate.get(Calendar.DAY_OF_MONTH)+"/"+cDate.get(Calendar.YEAR)+" "+((cDate.get(Calendar.HOUR)>0 && cDate.get(Calendar.HOUR)<10)?"0":"")+((cDate.get(Calendar.HOUR)==0)?12:cDate.get(Calendar.HOUR))+":"+((cDate.get(Calendar.MINUTE)<10)?"0":"")+cDate.get(Calendar.MINUTE)+":"+((cDate.get(Calendar.SECOND)<10)?"0":"")+cDate.get(Calendar.SECOND)+" "+ ((cDate.get(Calendar.AM_PM)==0)?"AM":"PM")+" "+(tz.equals("") || tz.equals(null)?"EST":tz);

		return currentDate;
	}
	//ended by shreyans
	 /*
	public static String getHours()
	{
		String hours="";
		for( int i=0 ; i< hours.length; i++)
		{

			hours += " <OPTION VALUE="+(i+1)+">"+hours[i] +"</OPTION>";
		}
		return hours;
	 }
*/
	
	public static String getHours() {
		String hour="";

		for( int i=0 ; i<24; i++){
			if(i<=9)
				hour += " <OPTION VALUE=0"+i+">0"+i +"</OPTION>";
			else
				hour += " <OPTION VALUE="+i+">"+i +"</OPTION>";
		}
		return hour;
	}
	
	
	public static String getMinutes(){
		String minute="";

		for( int i=0; i< 60; i++){
			if(i<=9)
				minute += " <OPTION VALUE=0"+i+">0"+i +"</OPTION>";
			else
				minute += " <OPTION VALUE="+i+">"+i +"</OPTION>";
		}
		
		return minute;
	}

	public static String getDateOfBirthDays(){

		String days="";
		
		for( int i=1 ; i<=31 ; i++){
			days += " <OPTION VALUE="+i+">"+i +" </OPTION>";
		}
		
		return days;
	}


	public static String getDays(){
		String days="";
		for( int i=1 ; i<=31 ; i++){
			days += " <OPTION VALUE="+i+">"+i;
		}
		return days;
	}
	
	
	public static String getDaysWithBlank(){
		String days="";
		days += " <OPTION VALUE= \"\">Day</OPTION>";
		
		for( int i=1 ; i<=31 ; i++){
			days += " <OPTION VALUE="+i+">"+i;
		}
		return days;
	}


	public static String getYears() {
		String years="";
		
		for( int i=1 ; i<=15 ; i++){
			if(i<10)
				years += " <OPTION VALUE=200"+i+">200"+i;
			if(i>=10)
				years +=" <OPTION VALUE=20"+i+">20"+i;
		}
		return years;
	}

	public static String getYearsWithBlank() {
		String years="";
		years += " <OPTION VALUE= \"\">Year</OPTION>";
		
		for( int i=1 ; i<=15 ; i++){
			if(i<10)
				years += " <OPTION VALUE=200"+i+">200"+i;
			if(i>=10)
				years +=" <OPTION VALUE=20"+i+">20"+i;
		}
		
		return years;
	}
	
	public static String getDateOfAdmit(){
		
		StringBuffer years=new StringBuffer(2000);
		Calendar  calendar =Calendar.getInstance();
		int y = calendar.get(Calendar.YEAR);
		
		for( int i=1980 ; i<= y; i++){
			years.append("<OPTION VALUE="+i+">");
			years.append(i);
		}
		return years.toString();
	 }

  
	public static String getDateOfBirthYears() {
		StringBuffer years=new StringBuffer(2000);
		Calendar  calendar =Calendar.getInstance();
		int y = calendar.get(Calendar.YEAR);
		
		for( int i=1900 ; i<= y; i++){
			years.append("<OPTION VALUE="+i+">");
			years.append(i);
		}
		return years.toString();
	}
	

	public static String getFaxForEfax(String s) {
		StringBuffer stringBuffer = new StringBuffer();
	
		for(int i=0;i<s.length();i++){
			char c1 = s.charAt(i);
			int c2 = (int)c1;
			
			switch(c2){
				case '0':
					stringBuffer.append(c1);
					break;
				case '1':
					stringBuffer.append(c1);
					break;
				case '2':
					stringBuffer.append(c1);
					break;
				case '3':
					stringBuffer.append(c1);
					break;
				case '4':
					stringBuffer.append(c1);
					break;
				case '5':
					stringBuffer.append(c1);
					break;
				case '6':
					stringBuffer.append(c1);
					break;
				case '7':
					stringBuffer.append(c1);
					break;
				case '8':
					stringBuffer.append(c1);
					break;
				case '9':
					stringBuffer.append(c1);
					break;
				default:
					break;
			}
		}
		
		if(stringBuffer.toString().length()<=10)
			return  "1"+stringBuffer.toString();
		else
			return stringBuffer.toString();
	
	}

	public static String getBreakStringFromString(String s) {
	
		StringBuffer stringBuffer = new StringBuffer();
		int chars=0;
		
		for(int i=0;i<s.length();i++) {
			chars=chars+1;
			char c1 = s.charAt(i);
			int c2 = (int)c1;
			
			switch(c2) {
				case ' ':				
					if(chars>60){
						stringBuffer.append("\n");
						chars=0;
					}else
						stringBuffer.append(c1);
					
					break;
				case '>':
					if(i>=3 && s.charAt(i-3)=='<'&&s.charAt(i-2)=='B'&&s.charAt(i-1)=='R') chars=0;
						stringBuffer.append(c1);
						
					break;
				case '\n':
					stringBuffer.append(c1);
					chars=0;
					break;
				default:
					stringBuffer.append(c1);
					break;
			}
		}
	 
		return stringBuffer.toString();
	}


	public static String getSystemDate() {
		Date d=new Date();
		return (d.getMonth()+1)+"/"+d.getDate()+"/"+(1900+d.getYear());
	}

	
	public static String getSystemDateLong(){
		DateFormat df = DateFormat.getDateInstance(DateFormat.LONG);
		Date d=new Date();
		return (df.format(d));
	}

	
	
	public static String getYesterdayDate(){
		Date d=new Date();
		Date yd=new Date(d.getYear(),d.getMonth(),(d.getDate()-1));
		return (yd.getMonth()+1)+"/"+yd.getDate()+"/"+(1900+yd.getYear());
	}


/// added by indra on 22/2/2005
	public static String getLastTwoDate() {
		Date d=new Date();
		Date yd=new Date(d.getYear(),d.getMonth(),(d.getDate()-2));
		return (yd.getMonth()+1)+"/"+yd.getDate()+"/"+(1900+yd.getYear());
	}


	public static String getLastWeekDate() {
		Date d=new Date();
		Date yd=new Date(d.getYear(),d.getMonth(),(d.getDate()-7));
		return (yd.getMonth()+1)+"/"+yd.getDate()+"/"+(1900+yd.getYear());
	}
	

	public static String getLastMonthDate(){
		Date d=new Date();
		Date yd=new Date(d.getYear(),d.getMonth()-1,(d.getDate()));
		return (yd.getMonth()+1)+"/"+yd.getDate()+"/"+(1900+yd.getYear());
	}



	public static String getTwoMonthDate(){
		Date d=new Date();
		Date yd=new Date(d.getYear(),d.getMonth()-2,(d.getDate()));
		return (yd.getMonth()+1)+"/"+yd.getDate()+"/"+(1900+yd.getYear());
	}

	
	public static String getThreeMonthDate() {
		Date d=new Date();
		Date yd=new Date(d.getYear(),d.getMonth()-3,(d.getDate()));
		return (yd.getMonth()+1)+"/"+yd.getDate()+"/"+(1900+yd.getYear());
	}


/// added by manoj  on 26/4/2005
	

	public static String getTomorrowDate() {
		Date d=new Date();
		Date yd=new Date(d.getYear(),d.getMonth(),(d.getDate()+1));
		return (yd.getMonth()+1)+"/"+yd.getDate()+"/"+(1900+yd.getYear());
	}


	public static String getNextTwoDays() {
		Date d=new Date();
		Date yd=new Date(d.getYear(),d.getMonth(),(d.getDate()+2));
		return (yd.getMonth()+1)+"/"+yd.getDate()+"/"+(1900+yd.getYear());
	}


	public static String getNextWeekDate(){
		Date d=new Date();
		Date yd=new Date(d.getYear(),d.getMonth(),(d.getDate()+7));
		return (yd.getMonth()+1)+"/"+yd.getDate()+"/"+(1900+yd.getYear());
	}
	
	public static String getNextMonthDate(){
		Date d=new Date();
		Date yd=new Date(d.getYear(),d.getMonth()+1,(d.getDate()));
		return (yd.getMonth()+1)+"/"+yd.getDate()+"/"+(1900+yd.getYear());
	}
	
	public static String getNextTwoMonthDate(){
		Date d=new Date();
		Date yd=new Date(d.getYear(),d.getMonth()+2,(d.getDate()));
		return (yd.getMonth()+1)+"/"+yd.getDate()+"/"+(1900+yd.getYear());
	}

	public static String getNextThreeMonthDate(){
		Date d=new Date();
		Date yd=new Date(d.getYear(),d.getMonth()+3,(d.getDate()));
		return (yd.getMonth()+1)+"/"+yd.getDate()+"/"+(1900+yd.getYear());
	}

//end of manoj added



	public static String getSystemDate1() {
		Date d=new Date();
		return (d.getMonth()+1)+"-"+d.getDate()+"-"+(1900+d.getYear());
	}

	public static String getSystemMonth(){
		Date d=new Date();
		//return ""+(d.getMonth()+1);
		String retVal=""+(d.getMonth()+1);
		if(retVal.length()==1) 
			retVal="0" + retVal;
		
		return retVal;
	}
	
	
	public static String getSystemDay(){
		Date d=new Date();
		//return ""+(d.getDate());
		String retVal=""+(d.getDate());
		if(retVal.length()==1) 
			retVal="0" + retVal;
		 
		return retVal;

	}
	
	public static String getSystemYear() {
		Date d=new Date();
		//return ""+(1900+d.getYear());
		String retVal=""+(1900+d.getYear());		
		if(retVal.length()==1) 
			retVal="0" + retVal;
		
		return retVal;

	  }




	public static String getSystemHours(){
		Date d=new Date();
		String retVal=""+(d.getHours());
		if(retVal.length()==1) retVal="0" + retVal;
		return retVal;
	}
	
	public static String getSystemMinutes() {
		Date d=new Date();
		String retVal=""+(d.getMinutes());
		if(retVal.length()==1) 
			retVal="0" + retVal;
		
		return retVal;
	}
	
	public static String getSystemSeconds() {
		Date d=new Date();
		String retVal=""+(d.getSeconds());
		
		if(retVal.length()==1) 
			retVal="0" + retVal;
		
		return retVal;
	}



	public static String getSystemMonthDayYearHourMinute(){
		return CommonFuncs.getSystemMonth()+"/"+CommonFuncs.getSystemDay()+"/"+CommonFuncs.getSystemYear()+" "+CommonFuncs.getSystemHours()+":"+CommonFuncs.getSystemMinutes();
	}

	public static String getSystemDateTimeWithHyphen(){
		return CommonFuncs.getSystemYear() + "-" + CommonFuncs.getSystemMonth() + "-" + CommonFuncs.getSystemDay() + " " + CommonFuncs.getSystemHours() + ":" + CommonFuncs.getSystemMinutes();
	}


	public static String get2DigitYears() {
		String years="";
		
		for( int i=1 ; i<=10 ; i++){
			if(i<10)
				years += " <OPTION VALUE= 0"+i+">200"+i+"</OPTION>";
			
			if(i>=10)
				years +=" <OPTION VALUE= "+i+">20"+i+"</OPTION>";
		}
		return years;
	}

	public static String get2DigitMonths() {
		String month="";
		
		for( int i=0 ; i< months.length; i++){
			if(i<10)
				month += " <OPTION VALUE= 0"+(i+1)+">"+months[i]+"</OPTION>";
			
			if(i>=10)
				month +=" <OPTION VALUE= "+i+1+">"+months[i]+"</OPTION>";

		}
		
		return month;
	}

	
	public static String get2DigitYears(String selectedValue){
		String years="",append="";
		
		for( int i=1 ; i<=10 ; i++){
			append="";
			
			if(i<10){
				if(selectedValue.equals("0"+i))
					append=" SELECTED";
			
				years += " <OPTION VALUE=0"+i+append+">200"+i+"</OPTION>";
			}
			
			if(i>=10){
				if(selectedValue.equals(""+i))
					append=" SELECTED";
			
				years +=" <OPTION VALUE= "+i+append+">20"+i+"</OPTION>";
			}
		}
		return years;
	}


	public static String get2DigitMonths(String selectedValue) {
		
		String month="",append="";
		
		for( int i=0 ; i< months.length; i++){
			append="";
			
			if(i<9){
				if(selectedValue.equals("0"+(i+1)))
					append=" SELECTED";
			
				month += " <OPTION VALUE=0"+(i+1)+append+">"+months[i]+"</OPTION>";
			}
			
			if(i>=9){
				if(selectedValue.equals(""+(i+1)))
					append=" SELECTED";

				month +=" <OPTION VALUE="+(i+1)+append+">"+months[i]+"</OPTION>";
			}
		}
		return month;
	}



	public static String get2DigitYearsWithBlank() {
		String years="";
		years += " <OPTION VALUE= \"\">Year</OPTION>";
		
		
		for( int i=1 ; i<=10 ; i++){
			if(i<10)
				years += " <OPTION VALUE= 0"+i+">200"+i+"</OPTION>";
			
			if(i>=10)
				years +=" <OPTION VALUE= "+i+">20"+i+"</OPTION>";
		}
		return years;
	}
	
	
	public static String get2DigitMonthsWithBlank(){
		String month="";
		month += " <OPTION VALUE= \"\">Month</OPTION>";
		
		for( int i=0 ; i< months.length; i++){
			if(i<9)
				month += " <OPTION VALUE= 0"+(i+1)+">"+months[i]+"</OPTION>";
			
			if(i>=9)
				month +=" <OPTION VALUE= "+(i+1)+">"+months[i]+"</OPTION>";

		}
		
		return month;
	}
	  
	public static String get2DigitDaysWithBlank(){	 		
		String days="";
	 	days += " <OPTION VALUE= \"\">Day</OPTION>";
	 		
	 	for( int i=1 ; i<=31 ; i++) {
	 		if(i<10)
	 			days += " <OPTION VALUE=0"+i+">"+i +" </OPTION>";
	 		else
	 			days += " <OPTION VALUE="+i+">"+i +" </OPTION>";
	 	}
	 	return days;
	}


/*
	public static String get2DigitYearsWithBlank(String selectedValue)
	{
		String years="",append="";
		years += " <OPTION VALUE= \"\">Year</OPTION>";
		for( int i=1 ; i<=10 ; i++)
		{
			if(i<10){
			if(selectedValue.equals("0"+i))
			append=" SELECTED";
			years += " <OPTION VALUE= 0"+i+append+"  >200"+i+"</OPTION>";
			}
			if(i>=10){
			if(selectedValue.equals(""+i))
			append=" SELECTED";
			years +=" <OPTION VALUE= "+i+append+">20"+i+"</OPTION>";
			}
		}
		return years;
	 }
	public static String get2DigitMonthsWithBlank(String selectedValue)
	{
		String month="",append="";
		month += " <OPTION VALUE= \"\">Month</OPTION>";
		for( int i=0 ; i< months.length; i++)
		{
			if(i<10){
				if(selectedValue.equals("0"+(i+1)))
			append=" SELECTED";

			month += " <OPTION VALUE= 0"+(i+1)+">"+months[i]+"</OPTION>";
			}
			if(i>=10){
				if(selectedValue.equals(""+(i+1)))
			append=" SELECTED";
			month +=" <OPTION VALUE= "+i+1+">"+months[i]+"</OPTION>";
			}

		}
		return month;
	 }
*/
	
	public static int getLastDayOfMonth(int month,int year){
		
		int lastDay=0;
	 
		switch (month) {
			case 1:
				lastDay=31; 
				break;
			case 2:
				lastDay=28;
				if (year%400==0 || (year%4 == 0 && year%100!=0) )
					lastDay=29;
				break;
			case 3:
				lastDay=31; 
				break;
			case 4:
				lastDay=30; break;
			case 5:
				lastDay=31; break;
			case 6:
				lastDay=30; break;
			case 7:
				lastDay=31; break;
			case 8:
				lastDay=31; break;
			case 9:
				lastDay=30; break;
			case 10:
				lastDay=31; break;
			case 11:
				lastDay=30; break;
			case 12:
				lastDay=31; break;
		}
	 
		return lastDay;
	}

		
	public static int getParsedIntValue(String value){
		int intvalue=0;

		if( value==null || value.equals("")){
			intvalue=0;
		}else{
			intvalue=Integer.parseInt(value);
		}
		
		return intvalue;
	}
	
	
	public static float getParsedFloatValue(String value){
		float floatvalue=0.0f;
	
		if( value==null || value.equals("")){
			floatvalue=0.0f;
		}else{
			floatvalue=Float.parseFloat(value);
		}
		return floatvalue;
	}


	public static String stripFileName(String fileName,String clinicLocalPath) {
		
		String fileNameInLower=fileName.replace('\\','/').toLowerCase();
		String clinicLocalPathInLower=clinicLocalPath.replace('\\','/').toLowerCase();
    
		if(fileNameInLower.length()>(clinicLocalPathInLower.length()+1)) {
		
			if(fileNameInLower.startsWith(clinicLocalPathInLower))
				fileName=fileName.substring(clinicLocalPathInLower.length()+1);
		}

		return fileName;

	}


 
	public static boolean isImage(String fileName){
		boolean resultval=false;
  
		if(fileName!=null){
			String  file=fileName.toLowerCase();
	
			if(fileName.endsWith(".gif")||fileName.endsWith(".jpg"))
				resultval=true;
		}
		return resultval;
	}


	public static String getDateString(String dateString){
		String date=dateString;
		if(date==null ||date.equals("")) 
			return null;
		else 
			return "'"+dateString+"'";
	}
/****
public static String getString(String textString)
{
	 String text=textString;
	 if(text==null ||text.equals("")) return null;
	  else return "'"+text+"'";
}
****/

	public static String replaceBySingleQuotes(String str){
		String replacedString="";
		if(str.length()>0){
			StringTokenizer st=new StringTokenizer(str,",");
			
			while(st.hasMoreTokens()){
				replacedString+=",'"+st.nextToken()+"'";
			}
			replacedString=replacedString.substring(1);
		}
		return replacedString;
	}



	public static Hashtable getDoctorTremplateArray(DbResultset dbResultset) {
	
		Hashtable finalHash=new Hashtable();
		int len=dbResultset.getRowCount();
		String doctorId="",key="",templateId="";

		for(int i=0;i<len;i++){
			doctorId=dbResultset.get(i,"DoctorId");
			templateId=dbResultset.get(i,"TemplateId");
			key=(String)finalHash.get(doctorId);
			
			if(key==null){
				finalHash.put(doctorId,templateId);
			}else{
				finalHash.put(doctorId,((String)finalHash.get(doctorId))+","+templateId);
			}
		}

		return finalHash;
	}

	public static String getMonthLabel(int month){

	 switch (month) {
		case 1:
			 return "JAN";
		case 2:
         	 return "FEB";

		case 3:
        return "MAR";

		case 4:
         return "APR";
		case 5:
         return "MAY" ;
		case 6:
         return "JUN" ;
		case 7:
         return "JUL" ;
		case 8:
         return "AUG" ;
		case 9:
         return "SEP" ;
		case 10:
         return "OCT" ;
		case 11:
         return "NOV" ;
		case 12:
         return "DEC" ;
	}
  return "";

	}


	public static String getDayLabel(String selectedDate){
		Date date=new Date(selectedDate);
		int day=date.getDay();
		switch (day) {
			case 0:
				 return "SUN";
			case 1:
				 return "MON";

			case 2:
	         	 return "TUE";

			case 3:
	        return "WED";

			case 4:
	         return "THU";
			case 5:
	         return "FRI" ;
			case 6:
	         return "SAT" ;
		}
		return "";

	}



	 public static String convertHexaToChar(String val) {

		 StringBuffer s = new StringBuffer(val);

		 for(int i= val.indexOf ("\\'",0);i!=-1;){
		  
			 if(i!=-1){
				 int j = s.charAt(i+2);
				 j -= (j<58 ? 48:(j<71 ? 55:87));
				 int k = s.charAt(i+3);
				 k -= (k<58 ? 48:(k<71 ? 55:87));
		 
				 if((""+j+k).equals("92") || (""+j+k).equals("91")){
					 s.replace(i,i+4,"'");
				 }else {
					 char c = (char)(j*16 + k);
					 s.replace(i,i+4,c+"");
				 }
				 
				 val=s.toString();
			 }
			 i = val.indexOf ("\\'",i);
		 }
		 return s.toString();
	 }

	 public static int getIndexOfFirstNonNumericChar(String filename) {

		 int i=0;
		 int len = filename.length();
		 byte[] b =filename.getBytes();
	
		 while(i< len){
			 if(b[i]<'0' || b[i]>'9')
				 break;
			 
			 i++;
		 }
		 return i;
	 }

	public static String skipRTFSource(String str)
	{
		StringBuffer stringBuffer = new StringBuffer();
		boolean skipflag=false;
		for(int i=0;i<str.length();i++)
		{
			char c1 = str.charAt(i);
			int c2 = (int)c1;
			switch(c2)
			{

				case '}':
					break;
				case '{':
					break;
				case '\\':
					skipflag=true;
					break;

				case ' ':
					if(skipflag==true){
					skipflag=false;
					}else{
					stringBuffer.append(c1);
					}
					break;
				default:
					if(skipflag==false)
					stringBuffer.append(c1);
					break;
			}
		}
		return stringBuffer.toString();
	}


public static String convertMinToHours(String time){
		//if(time==null || time.equals("")||time.indexOf("-")!=-1) return "";
		if(time==null || time.equals("")) return "";
		int minTime = Integer.parseInt(time);
		if(time.indexOf("-")!=-1) minTime = 0-minTime;
		int hour	= minTime /60 ;
		minTime = minTime % 60;

		String showTime = hour+":"+((minTime==0||minTime<10)?"0"+minTime:""+minTime);
		if(time.indexOf("-")!=-1) showTime = "-"+ showTime;

		return showTime;
}

public static String convertTimeIntoSSAMPM(String time)
{
	String t="";
	if(time ==null || time.equals(""))
	return t;
	//String time="13:00:00";    //1:00 PM
	int d1 = time.indexOf(":");
	int d2 = time.indexOf(":",d1+1);
//	int d3 = time.lastIndexOf(":");
	int h=Integer.parseInt(time.substring(0,d1));
	int m=Integer.parseInt(time.substring(d1+1,d2));
	String s=time.substring(d2+1);
	String ampm=(h>=12 )?"PM":"AM";
	if(h>12)h-=12;
    t = ""+h + ":"+((m<10)?"0":"") + m +":"+s+" "+ ampm;
    return t;
}
public static String convertTimeIntoAMPM(String time)
{
	String t="";
	if(time ==null || time.equals(""))
	return t;
	
	//String time="13:00:00";    //1:00 PM
	int d1 = time.indexOf(":");
	int d2 = time.indexOf(":",d1+1);
	int h=Integer.parseInt(time.substring(0,d1));
	int m=Integer.parseInt(time.substring(d1+1,d2));
	String ampm=(h>=12 )?"PM":"AM";
	if(h>12)h-=12;
    t = ""+h + ":"+((m<10)?"0":"") + m +" "+ ampm;
    return t;
}

public static String convertMinsToHourAMPM(String time){

	if(time==null || time.equals("")) return "";
	String ampm = "AM";
	int hour	= Integer.parseInt(time) /60 ;
	if(hour >=12) ampm = "PM";
	if(hour >12)  hour = hour -12;

	int min 	= Integer.parseInt(time) % 60 ;

	return (hour+":"+((min==0 || min==5)?"0"+min:""+min)+" "+ampm);
}


  public  static String convert2USDateWithHourMinuteAMPM(String date) {
   	if(date==null || date.equals(""))return "";
   	if(date.indexOf("-")==-1)
   	  return date;
    int d1 = date.indexOf("-");
   	int d2 = date.indexOf("-",d1+1);
   	int d3 = date.indexOf(" ");
   	int d4 = date.lastIndexOf(":");
   	String time=convertTimeIntoAMPM(date.substring(d3,d4).trim()+":00");
   	try{
   			return Integer.parseInt(date.substring(d1+1,d2)) + "/" + Integer.parseInt(date.substring(d2+1,d3)) + "/" + date.substring(0,d1)+" "+time;
   		}catch(Exception e)
   		{
   			e.printStackTrace(System.out);
   		 return date;
   		}

  }
  
  public  static String convert2USDatetoOnlyHourMinuteAMPM(String date) {
	   	if(date==null || date.equals(""))return "";
	   	int d3 = date.indexOf(" ");
	   	int d4 = date.lastIndexOf(":");
	   	String time=convertTimeIntoAMPM(date.substring(d3,d4).trim()+":00");
	   	return time;
	  }

  public  static String convert2USDateWithHourMinuteSSAMPM(Date date) {
	   	try{
	   		return dateformat.format(date);
	   		}catch(Exception e)
	   		{
	   		 return "";
	   		}

	  }
  
  public static String convertDateToEDIFormat(String date) {
		
	  String ediDateFormat = "";
	  ediDateFormat =  date.replaceAll("-", "").split(" ")[0];
	  return ediDateFormat;
	  
	}


  public  static String getTimeOfService(String date)
  {
     	//date= "2004-05-17 23:00:00.000";
     	if(date==null || date.equals(""))return "";
		int d1 = date.indexOf(" ");
     	int d2 = date.lastIndexOf(":");

     	//String time=convertTimeIntoAMPM(date.substring(d1,d2).trim()+":00");
     	String time="";
     	if((d1!=-1)&&(d2!=-1))time=convertTimeIntoAMPM(date.substring(d1,d2).trim()+":00");
     	return time;
   }




/*
Format Pattern                         Result
 --------------                         -------
 "yyyy.MM.dd G 'at' hh:mm:ss z"    ->>  1996.07.10 AD at 15:08:56 PDT
 "EEE, MMM d, ''yy"                ->>  Wed, July 10, '96
 "h:mm a"                          ->>  12:08 PM
 "hh 'o''clock' a, zzzz"           ->>  12 o'clock PM, Pacific Daylight Time
 "K:mm a, z"                       ->>  0:00 PM, PST
 "yyyyy.MMMMM.dd GGG hh:mm aaa"    ->>  1996.July.10 AD 12:08 PM



	public static String getDateString(Date date,String format)
	{
	 if(date==null)	return "";
	  	SimpleDateFormat currentDateFormate  = new SimpleDateFormat (format);
	 	return   currentDateFormate.format(date);

	}

	public static Date getDate(String dateString,String format)throws ParseException
	{
	 Date date=null;
	 try{
	  SimpleDateFormat currentDateFormate  = new SimpleDateFormat (format);
	  date=currentDateFormate.parse(dateString);
       }catch(ParseException pe){ }
       return date;
    }


    public static String getFormatedDateString(String dateString,String format)throws java.text.ParseException
    {
	   Date d=getDate(dateString,sqlServerDateFormat)	;
	   return getDateString(d,format);
	}


	public static Date addDate(Date date,int field,int num)
	{
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(field,num);
		return calendar.getTime();
	}
	**/




/*********SHUBRA *************/

/*public static String get2DigitMinutes(int min)
{
	if (min<10)
	return "0"+min;
	else
	return ""+min;
}

public static String getTimeOfAppointment(String dateTimeString,String format)throws ParseException
{
	if (dateTimeString.equals(""))
		dateTimeString=  getDateString(new Date(),format);
		Date date=getDate(dateTimeString,format);
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		String ampm=calendar.get(Calendar.AM_PM)==1?"PM":"AM";
		return (calendar.get(Calendar.HOUR)+":"+get2DigitMinutes(calendar.get(Calendar.MINUTE))+" "+ampm);
		//return "12:00";
}

public static String getMonthOrDayOrYear(String dateTimeString,String format,int field)throws ParseException
{
	if (dateTimeString.equals(""))
		dateTimeString=  getDateString(new Date(),format);
		Date date=getDate(dateTimeString,format);
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		//int field= calendar.get(field);
			return ""+calendar.get(field);
}


public static String printMonthsSelect(String selectedValue)
	{
		selectedValue=""+(Integer.parseInt(selectedValue)+1);
		String month="",append="";
		for( int i=1; i<= months.length; i++)
		{
			append="";
			if(selectedValue.equals(""+i))
			append=" SELECTED";
			month += " <OPTION VALUE="+i+append+">"+months[i-1]+"</OPTION>";
		}


		return month;
	 }
public static String printDaysSelect(String selectedValue)
{
	String days="";
			for( int i=1 ; i<=31 ; i++)
			{

				 days += " <OPTION VALUE="+i;
				 if(selectedValue.equals(""+i))
				 days+=" SELECTED";
				 days+=">"+i +" </OPTION>";

			}
		return days;

}

public static String printYearSelect(String selectedValue)
{
	String years="";
			for( int i=1 ; i<=10 ; i++)
			{
				if(i<10)
				{
				 years += " <OPTION VALUE=200"+i;
				 if(selectedValue.equals("200"+i))
				 years+=" SELECTED";
				  years+=">200"+i +" </OPTION>";
				}
				else
				{
				 years += " <OPTION VALUE=20"+i;
				 if(selectedValue.equals(""+i))
				 years+=" SELECTED";
				 years+=">20"+i +" </OPTION>";
				}


			}
		return years;

}


public static String printSelectOptionsTime(String selectedValue,int interval)
{
	StringBuffer output = new StringBuffer();
	GregorianCalendar gc=new GregorianCalendar();
	gc.set(Calendar.HOUR_OF_DAY,0);
	gc.set(Calendar.MINUTE,0);
	String hrsMin="";
	String ampm="";
	while(!(hrsMin.equals("0:00 AM")))
	{
	 	ampm=gc.get(Calendar.AM_PM)==1?"PM":"AM";
		hrsMin=gc.get(Calendar.HOUR)+":"+get2DigitMinutes(gc.get(Calendar.MINUTE))+" "+ampm;
		output.append("\n<option value=\""+hrsMin);

			 if(hrsMin.equalsIgnoreCase(selectedValue))
				 output.append("\" SELECTED>");
			 else
				 output.append("\">");
			 output.append(hrsMin);


			gc.roll(Calendar.MINUTE,interval);
			if(gc.get(Calendar.MINUTE)==0)
			gc.roll(Calendar.HOUR_OF_DAY,1);
		ampm=gc.get(Calendar.AM_PM)==1?"PM":"AM";
		hrsMin=gc.get(Calendar.HOUR)+":"+get2DigitMinutes(gc.get(Calendar.MINUTE))+" "+ampm;
	}
	return output.toString();
}

*/
public static int getMinutes(String dateTimeString,String format)throws ParseException
{
	//DateFormat df=DateFormat.getDateInstance(sqlServerDateFormat);
	SimpleDateFormat df  = new SimpleDateFormat (format);
	Date date=df.parse(dateTimeString);
	Calendar calendar=Calendar.getInstance();
	calendar.setTime(date);
	int min=calendar.get(Calendar.MINUTE);
	int hrs=calendar.get(Calendar.HOUR_OF_DAY );
	return min+hrs*60;
}

public static String[] getWeekDates(String selectedDate)
{
	String[] dates=new String[7];
	Date date=new Date(selectedDate);
	Calendar cal=Calendar.getInstance();
	Date d=null;
	cal.setTime(date);
	cal.setFirstDayOfWeek(1);
	int selectedDay=cal.get(Calendar.DAY_OF_WEEK);
	if (selectedDay!=1)
		cal.add(Calendar.DATE,1-selectedDay);
	d=cal.getTime();
	dates[0]=(d.getMonth()+1)+"/"+d.getDate()+"/"+(1900+d.getYear());
	for(int i=1;i<7;i++)
	{
		cal.add(Calendar.DATE,1);
		d=cal.getTime();
		dates[i]=(d.getMonth()+1)+"/"+d.getDate()+"/"+(1900+d.getYear());
	}
	return dates;

}


// Changed by mehul for Appointment Slot
public static String getTimeFromMin(int totalMins, boolean compressed, boolean firstActiveSlot)
{
	char hrs1=1+'0';
	char hrs2=2+'0';
	char mer = 'A';
	int h = totalMins/60;
	int m = totalMins%60;
	
	String amPm = " AM";
	String hour=null;
	String min=null;
	String sep = ":";
	
	if(h>=12) {
		amPm=" PM";
	}
	
	if(h>12 && h<=23){
		h-=12;
	}
	
	if(h<=9){
		hour = "0"+h;
	}else{
		hour = h+"";
	}
	
	if(m<=9){
		min = "0"+m;
	}else{
		min = m+"";
	}
	
	return hour+sep+min+amPm;
}
//Changed by mehul for Appointment Slot

public static String printSelectOptionsTime(String dos)
{
	StringBuffer timeOptions=new StringBuffer(5400);
	timeOptions.append("<option value="+dos+">"+dos+"</option>");

	return timeOptions.toString();
}


public static String printSelectOptionsTime()
{
	StringBuffer timeOptions=new StringBuffer(5400);
	int hrs=0;
	int min=0;
	boolean am=true;
	//timeOptions.append("<option value='0:00 AM'>0:00 AM</option>");
	timeOptions.append("<option>0:00 AM");
	for(int i=1;i<24*12;i++)
	{
		min+=5;
		if(min==60)
		{
			hrs++;
			// changed hrs==13 to hrs==12
			if(hrs==13)
			{
				hrs=1;

			}
			if(hrs==12)
			{
				am=false;
			}
			min=0;

		}
		String value=hrs+":"+((min<10)?"0":"")+min+" "+((am)?"AM":"PM");
		//timeOptions.append("<option value='"+value+"'>"+value+"</option>");
		timeOptions.append("<option>"+value);

	}

	return timeOptions.toString();
}



public static String printSelectOptionsTime(int interval)
{
	StringBuffer timeOptions=new StringBuffer(5400);
	int hrs=0;
	int min=0;
	boolean am=true;
	timeOptions.append("<option value=0>0:00 AM</option>");
	for(int i=1;i<24*60/interval;i++)
	{
		min+=interval;
		if(min==60)
		{
			hrs++;
			if(hrs==12)
			{
				am=false;
			}
			if(hrs==13)
			{
				hrs=1;
			}
			min=0;

		}
		timeOptions.append("<option value="+i+">"+hrs+":"+((min<10)?"0":"")+min+" "+((am)?"AM":"PM")+"</option>");

	}

	return timeOptions.toString();
}



public static DbResultset convertToDbResultSet(ResultSet rs) throws Exception
{
	ResultSetMetaData rsmd = rs.getMetaData();
	int columnCount = rsmd.getColumnCount();
	Hashtable columnNames = new Hashtable();
	DbResultset dbResult=null;
	int totalRows=0;

	try{
	for(int i=1;i<=columnCount;i++)
	{
		columnNames.put(rsmd.getColumnName(i),new Integer(i-1));
	}
	Vector rowWiseData = new Vector(50);

	while(rs.next())
	{
		Vector tempDataVector = new Vector(columnCount);
		for(int i=1; i<=columnCount; i++)
		{
			String resultData = rs.getString(i);
			if(resultData== null)
			{
				resultData="";
			}
			tempDataVector.addElement(resultData);
		}
		rowWiseData.addElement(tempDataVector);
		totalRows++;
	}

	if(!rowWiseData.isEmpty())
	{
	  dbResult=new DbResultset(columnNames, rowWiseData,totalRows);
	}
	else
		dbResult = new DbResultset();
	}catch(Exception e){
	   e.printStackTrace(System.out);
	}
	finally{
	    rs.close();
	}

	return dbResult;
}


	public static String getDateOfServiceScriptArray(String[] dateOfServiceList)
	{
			String dateOfServiceArray="";
			if(dateOfServiceList == null ||  dateOfServiceList.length==0)
		  	{
		 		dateOfServiceArray="var dateOfServiceArray=new Array()";
				return  dateOfServiceArray;
		  	}
		  	dateOfServiceArray="var dateOfServiceArray=new Array(";
			for(int i=0;i<dateOfServiceList.length;i++){
				if(dateOfServiceList[i]==null) continue;
				//if(i==dateOfServiceList.length-1)
				//	dateOfServiceArray += dateOfServiceList[i];
				//else
					dateOfServiceArray += dateOfServiceList[i]+",";
			}
			if(dateOfServiceArray.indexOf(",")!=-1)
		    dateOfServiceArray=dateOfServiceArray.substring(0,dateOfServiceArray.length()-1);
/*****
			for(int i=0;i<dateOfServiceList.getRowCount();i++)
		    {
				String appointmentId=dateOfServiceList.get(i,"AppointmentId");
				String chiefComplaint=dateOfServiceList.get(i,"ChiefComplaint");
				String dateOfService=dateOfServiceList.get(i,"AppointmentDate");
				//String timeOfService=dateOfServiceList.get(i,"StartTime");
				String timeOfService=CommonFuncs.convertTimeIntoAMPM(dateOfServiceList.get(i,"StartTime"));
			    if(i==dateOfServiceList.getRowCount()-1)
		 	    {
		 	      ////dateOfServiceArray+="[\""+appointmentId+"\",\""+dateOfService+"\",\""+timeOfService+"\",\""+chiefComplaint+"\"]";
		 	      dateOfServiceArray+="\""+appointmentId+";"+dateOfService+";"+timeOfService+";"+chiefComplaint+"\"";
		 	    }
	            else
		        ////dateOfServiceArray+="[\""+appointmentId+"\",\""+dateOfService+"\",\""+timeOfService+"\",\""+chiefComplaint+"\"],";
		        dateOfServiceArray+="\""+appointmentId+";" +dateOfService +";"+timeOfService+";"+chiefComplaint+"\",";
			}
*******/
			 dateOfServiceArray+=");";
			 
			 
			 return  dateOfServiceArray;
	}
	public static String getDateOfServiceScriptArrayNew(String[] dateOfServiceList)
	{
			String dateOfServiceArray="";
			if(dateOfServiceList == null ||  dateOfServiceList.length==0)
		  	{
		 		dateOfServiceArray="new Array()";
				return  dateOfServiceArray;
		  	}
		  	dateOfServiceArray="new Array(";
			for(int i=0;i<dateOfServiceList.length;i++){
				if(dateOfServiceList[i]==null) continue;
				//if(i==dateOfServiceList.length-1)
				//	dateOfServiceArray += dateOfServiceList[i];
				//else
					dateOfServiceArray += dateOfServiceList[i]+",";
			}
			if(dateOfServiceArray.indexOf(",")!=-1)
		    dateOfServiceArray=dateOfServiceArray.substring(0,dateOfServiceArray.length()-1);
/*****
			for(int i=0;i<dateOfServiceList.getRowCount();i++)
		    {
				String appointmentId=dateOfServiceList.get(i,"AppointmentId");
				String chiefComplaint=dateOfServiceList.get(i,"ChiefComplaint");
				String dateOfService=dateOfServiceList.get(i,"AppointmentDate");
				//String timeOfService=dateOfServiceList.get(i,"StartTime");
				String timeOfService=CommonFuncs.convertTimeIntoAMPM(dateOfServiceList.get(i,"StartTime"));
			    if(i==dateOfServiceList.getRowCount()-1)
		 	    {
		 	      ////dateOfServiceArray+="[\""+appointmentId+"\",\""+dateOfService+"\",\""+timeOfService+"\",\""+chiefComplaint+"\"]";
		 	      dateOfServiceArray+="\""+appointmentId+";"+dateOfService+";"+timeOfService+";"+chiefComplaint+"\"";
		 	    }
	            else
		        ////dateOfServiceArray+="[\""+appointmentId+"\",\""+dateOfService+"\",\""+timeOfService+"\",\""+chiefComplaint+"\"],";
		        dateOfServiceArray+="\""+appointmentId+";" +dateOfService +";"+timeOfService+";"+chiefComplaint+"\",";
			}
*******/
			 dateOfServiceArray+=");";
			 return  dateOfServiceArray;
	}

	public static String getCaseNameScriptArray(String[] caseNameList)
	{
			String caseNameArray="";
			if(caseNameList == null ||  caseNameList.length==0)
		  	{
				caseNameArray="var caseNameArray=new Array()";
				return  caseNameArray;
		  	}
			caseNameArray="var caseNameArray=new Array(";
			for(int i=0;i<caseNameList.length;i++){
				if(caseNameList[i]==null) continue;
				caseNameArray += caseNameList[i]+",";
			}
			if(caseNameArray.indexOf(",")!=-1)
				caseNameArray=caseNameArray.substring(0,caseNameArray.length()-1);
				caseNameArray+=");";
			return  caseNameArray;
	}
	
	
	public static String getCaseTypeScriptArray(String[] caseTypeList)
	{
			String caseTypeArray="";
			if(caseTypeList == null ||  caseTypeList.length==0)
		  	{
				caseTypeArray="var caseTypeArray=new Array()";
				return  caseTypeArray;
		  	}
			caseTypeArray="var caseTypeArray=new Array(";
			for(int i=0;i<caseTypeList.length;i++){
				if(caseTypeList[i]==null) continue;
				caseTypeArray += caseTypeList[i]+",";  
			}
			if(caseTypeArray.indexOf(",")!=-1)
				caseTypeArray=caseTypeArray.substring(0,caseTypeArray.length()-1);
			caseTypeArray+=");";
			return  caseTypeArray;
	}  
	
	
	
	public static String getCaseNameScriptArrayNew(String[] caseNameList)
	{
			String caseNameArray="";
			if(caseNameList == null ||  caseNameList.length==0)
		  	{
				caseNameArray=" new Array()";
				return  caseNameArray;
		  	}
			caseNameArray="new Array(";
			for(int i=0;i<caseNameList.length;i++){
				if(caseNameList[i]==null) continue;
				caseNameArray += caseNameList[i]+",";
			}
			if(caseNameArray.indexOf(",")!=-1)
				caseNameArray=caseNameArray.substring(0,caseNameArray.length()-1);
				caseNameArray+=");";
			return  caseNameArray;
	}	
	
		public static String getAge(String dob) {
			int ageDays=0; int ageWeeks=0;int ageMths=0; int ageYrs=0;
			int k =dob.indexOf("-");
			if(k==-1) return "";
			String year  = dob.substring(0,4);
			String month = dob.substring(5,7);
			String days  = dob.substring(8,10);

			Date a=new java.util.Date();
			int ayear  = a.getYear()+1900;
			int amonth = a.getMonth()+1;
			int adate   = a.getDate();

			Date b=new java.util.Date(month+"/"+days+"/"+year);
			int byear  = b.getYear()+1900;
			int bmonth = b.getMonth()+1;
			int bdate   = b.getDate();

			int lastDate = getLastDayOfMonth(bmonth,byear);

			ageDays = adate - bdate;
			if(ageDays<0){
				ageDays = (lastDate - bdate) + adate;
				amonth--;
			}

			ageMths = amonth - bmonth;
			if(ageMths<0){
				ageMths = (12 - bmonth) + amonth;
				ayear--;
			}

			ageYrs = ayear - byear;
			if(ageYrs<0)
				return "0 day";

			if(ageYrs==0 && ageMths==0){
				ageWeeks= ageDays / 7;
				if(ageWeeks==0){
					if(ageDays==1) return "1 day";
						else return ageDays+" days";
					}else{
						if(ageWeeks==1) return "1 week";
						else return ageWeeks+" weeks";
					}
			}else{
				String yrs = " yrs ", mths = " mths";
				if(ageYrs == 1) yrs = " yr ";
				if(ageMths == 1) mths = " mth ";

				if(ageYrs == 0) return ageMths + mths;
				if(ageYrs != 0 && ageMths == 0) return ageYrs + yrs;
				if(ageYrs >= 18) return ageYrs + yrs;

				return ageYrs + yrs + ageMths + mths;
			}
	}




	public static String getAgeToday(String dob)
	{
			int ageDays=0; int ageWeeks=0;int ageMths=0; int ageYrs=0;
			int k =dob.indexOf("-");
			if(k==-1) return "";
			String year  = dob.substring(0,4);
			String month = dob.substring(5,7);
			String days  = dob.substring(8,10);

			Date a=new java.util.Date();
			int ayear  = a.getYear()+1900;
			int amonth = a.getMonth()+1;
			int adate   = a.getDate();

			Date b=new java.util.Date(month+"/"+days+"/"+year);
			int byear  = b.getYear()+1900;
			int bmonth = b.getMonth()+1;
			int bdate   = b.getDate();

			int lastDate = getLastDayOfMonth(bmonth,byear);

			ageDays = adate - bdate;
			if(ageDays<0){
				ageDays = (lastDate - bdate) + adate;
				amonth--;
			}

			ageMths = amonth - bmonth;
			if(ageMths<0){
				ageMths = (12 - bmonth) + amonth;
				ayear--;
			}

			ageYrs = ayear - byear;
			if(ageYrs<0)
				return "0 day";

			if(ageYrs==0 && ageMths==0){
				ageWeeks= ageDays / 7;
				if(ageWeeks==0){
					if(ageDays==1) return "1d";
						else return ageDays+" d";
					}else{
						if(ageWeeks==1) return "1w";
						else return ageWeeks+"w";
					}
			}else{
				String yrs = "y ", mths = "m";
				if(ageYrs == 1) yrs = "y ";
				if(ageMths == 1) mths = "m";

				if(ageYrs == 0) return ageMths + mths;
				if(ageYrs != 0 && ageMths == 0) return ageYrs + yrs;
				if(ageYrs >= 10) return ageYrs + yrs;

				return ageYrs + yrs + ageMths + mths;
			}
	}

	public static String getDaysFromDate(String dob){

		int ageDays=0; int ageWeeks=0;int ageMths=0; int ageYrs=0;
		int k =dob.indexOf("/");
		if(k==-1) return "";

		String[] dateParts=dob.split("/");


		String year  = dateParts[2];
		String month = dateParts[0];
		String days  = dateParts[1];

		Date a=new java.util.Date();
		int ayear  = a.getYear()+1900;
		int amonth = a.getMonth()+1;
		int adate   = a.getDate();

		Date b=new java.util.Date(month+"/"+days+"/"+year);
		int byear  = b.getYear()+1900;
		int bmonth = b.getMonth()+1;
		int bdate   = b.getDate();


		long im=a.getTime()-b.getTime();
		long noOfDays=im/(1000*60*60*24);

		String numRange="";

				if(noOfDays>=0 && noOfDays<=30) numRange="30";
				if(noOfDays>=31 && noOfDays<=60) numRange="60";
				if(noOfDays>=61 && noOfDays<=90) numRange="90";
				if(noOfDays>=91) numRange="90+";

		return numRange;

    }


	public static String getLocation(String siteId,HttpSession session)
	{
		String location="";
		if(siteId==null || siteId.equals(""))
		  siteId=(String)session.getValue("siteId");
		String sites[][]=(String [][])session.getValue("sites");
		for(int i=0; i< sites[0].length;i++){
			if(sites[0][i].equals(siteId))
			location=sites[2][i]+" "+sites[1][i];
		}
		return location;
	}


	public static String getProviderName(String providerId,DbResultset dbResultset)
	{
		String providerName="";
		for(int j=0;j<dbResultset.getRowCount();j++){
			if((dbResultset.get(j,"DoctorId").trim()).equalsIgnoreCase(providerId)){
				providerName=dbResultset.get(j,"DoctorName");
				break;
		 	}
		}
		return providerName;
	}

	public static String formatedDate(String date, String pattern)
	  {
		  java.util.Date d=new java.util.Date(date);
		  java.text.DateFormat dateFormat = new SimpleDateFormat(pattern);
		  String str = dateFormat.format(d);
		  return str;
	  }

	public static String formatedDate(java.util.Date date, String pattern)
	  {
		  java.util.Date d=new java.util.Date(date.getYear(), date.getMonth(),date.getDate());
		  java.text.DateFormat dateFormat = new SimpleDateFormat(pattern);
		  String str = dateFormat.format(d);
		  return str;
	  }

	public static String getFormatedDate(Calendar c1)
	{
			String dateFormat = "MM/dd/yyyy";
			SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
			String newdate = sdf.format(c1.getTime());//get current date
			return newdate;
   }

   public static String replaceEnterWithBR(String str)
   { 			StringBuffer stringBuffer = new StringBuffer();
   			for(int i=0;i<str.length();i++)
   			{
   				char c1 = str.charAt(i);
   				int c2 = (int)c1;
   				switch(c2)
   				{
   					case '\n':
   						stringBuffer.append("<BR>");
   						break;
   					default:
						stringBuffer.append(c1);
						break;
   				}
   			}
   			return stringBuffer.toString();
   }

    public static String replaceEnterWithSpace(String str)
    { 			StringBuffer stringBuffer = new StringBuffer();
      			for(int i=0;i<str.length();i++)
      			{
      				char c1 = str.charAt(i);
      				int c2 = (int)c1;
      				switch(c2)
      				{
      					case '\n':
      						stringBuffer.append(" ");
      						break;
      					case '\r':
							break;
      					default:
   						stringBuffer.append(c1);
   						break;
      				}
      			}
      			return stringBuffer.toString();
   }




  public static String getDocumentsDone(int attrib)
  {
		StringBuffer sb=new StringBuffer();
		String str="";
		if( (attrib & CommonFuncs.ATTRIB_HPI)!=0) sb.append("HPI, ");
		if( (attrib & CommonFuncs.ATTRIB_ROS)!=0) sb.append("ROS, ");
		if( (attrib & CommonFuncs.ATTRIB_PHYEXAM)!=0) sb.append("PE, ");
		if( (attrib & CommonFuncs.ATTRIB_ASSMPLAN)!=0) sb.append("A&P, ");
		if( (attrib & CommonFuncs.ATTRIB_LABORDER)!=0) sb.append("LAB, ");
		if(sb.indexOf(",")!=-1)
		str=sb.substring(0,sb.length()-2);

		return str;
  }

  public static String getChiefComplaintListArray(DbResultset dateOfServiceList)
  {
  			String dateOfServiceArray="";
  			if(dateOfServiceList == null ||  dateOfServiceList.getRowCount()==0)
  		  	{
  		 		dateOfServiceArray="var dateOfServiceArray=new Array()";
  				return  dateOfServiceArray;
  		  	}
  		  	dateOfServiceArray="var dateOfServiceArray=new Array(";
  			for(int i=0;i<dateOfServiceList.getRowCount();i++)
  		    {
  				String appointmentId=dateOfServiceList.get(i,"AppointmentId");
  				String chiefComplaint=dateOfServiceList.get(i,"ChiefComplaint");
  				String dateOfService=dateOfServiceList.get(i,"AppointmentDate");
  				//String timeOfService=dateOfServiceList.get(i,"StartTime");
  				String timeOfService=CommonFuncs.convertTimeIntoAMPM(dateOfServiceList.get(i,"StartTime"));
  			    if(i==dateOfServiceList.getRowCount()-1)
  		 	    {
  		 	      ////dateOfServiceArray+="[\""+appointmentId+"\",\""+dateOfService+"\",\""+timeOfService+"\",\""+chiefComplaint+"\"]";
  		 	      dateOfServiceArray+="\""+appointmentId+";"+dateOfService+";"+timeOfService+";"+chiefComplaint+"\"";
  		 	    }
  	            else
  		        ////dateOfServiceArray+="[\""+appointmentId+"\",\""+dateOfService+"\",\""+timeOfService+"\",\""+chiefComplaint+"\"],";
  		        dateOfServiceArray+="\""+appointmentId+";" +dateOfService +";"+timeOfService+";"+chiefComplaint+"\",";
  			}
  			dateOfServiceArray+=");";
  			return  dateOfServiceArray;
  }

  public static String replaceNL(String line){
  		int index;
  		while ((index=line.indexOf('\n'))!=-1){
  			line=line.substring(0,index)+"\\n"+line.substring(index+1,line.length());
  		}
  		return line;
	}

	public static String replace(String psWord, String psReplace, String psNewSeg){
	   	  StringBuffer lsNewStr = new StringBuffer();
	 	  int liFound = 0;
		  int liLastPointer=0;
		  do {
			   liFound = psWord.indexOf(psReplace, liLastPointer);
				   if ( liFound < 0 )
					  lsNewStr.append(psWord.substring(liLastPointer,psWord.length()));
				   else {
					  if (liFound > liLastPointer)
						 lsNewStr.append(psWord.substring(liLastPointer,liFound));
					  lsNewStr.append(psNewSeg);
					  liLastPointer = liFound + psReplace.length();
				   }

		  }while (liFound > -1);
	 return lsNewStr.toString();
	}



/*
	public static String Replace(String psWord, String psReplace, String psNewSeg){
	   	  StringBuffer lsNewStr = new StringBuffer();
	 	  int liFound = 0;
		  int liLastPointer=0;
		  do {
			   liFound = psWord.indexOf(psReplace, liLastPointer);
				   if ( liFound < 0 )
					  lsNewStr.append(psWord.substring(liLastPointer,psWord.length()));
				   else {
					  if (liFound > liLastPointer)
						 lsNewStr.append(psWord.substring(liLastPointer,liFound));
					  lsNewStr.append(psNewSeg);
					  liLastPointer = liFound + psReplace.length();
				   }

		  }while (liFound > -1);
	 return lsNewStr.toString();
	}
*/

/*
   public static String listWithBR(String list){
        if(list.equals("")) return "";
		StringTokenizer st = new StringTokenizer(list, ",");
		String listWithBRString="";
		int i = 0;
		while (st.hasMoreTokens()) {
		listWithBRString += st.nextToken()+"<BR>";
			i++;
      }
		return listWithBRString.substring(0,listWithBRString.lastIndexOf("<BR>"));
	}

*/
	public static String  getStateAbbr(String stateFullName)
	{
		for(int i=0; i< state.length; i++)
			if(state[i].equalsIgnoreCase(stateFullName)) return stateAbbr[i];
		return "";
	}
	public static String  getStateFromAbbr(String stAbbr)
	{
		for(int i=0; i< stateAbbr.length; i++)
			if(stateAbbr[i].equalsIgnoreCase(stAbbr)) return state[i];
		return "";
	}
	
	public static String  getCurrentDateInSQLForm()
	{
		String curDate=(new Timestamp((new Date()).getTime())).toString();
		for (int i=curDate.length();i<23;i++)curDate=curDate+"0";
		return curDate;
	}

	public static String  getCurrentDateInSQLForm(Date dt)
	{
		String curDate=(new Timestamp(dt.getTime())).toString();;
		for (int i=curDate.length();i<23;i++)curDate=curDate+"0";
		return curDate;
	}
	
   public static String getEntityId(String controller)
   	{
   		String[][] entityIdArr=
   		{
   			{"AppointmentController","appointmentId"},
   			{"ApptOptionController","apptOptionId"},
   			{"BillingController","billingId"},
   			{"DoctorController","doctorId"},
   			{"EMRController","recordId"},
   			{"EligibilitySettingsController","eligibilitySettingsId"},
   			{"InsuranceCarrierController","insuranceCarrierId"},
   			{"LabOrderController","labOrderId"},
   			{"MasterCarrierProviderInfoController","masterCarrierProviderInfoId"},
   			{"MessageController","messageId"},
   			{"PatientAllergyController","patientAllergyId"},
   			{"PatientController","patientId"},
   			{"PatientExaminationController","patientExaminationId"},
   			{"PatientHistoryController","patientHistoryId"},
   			{"PatientInsuranceController","patientInsuranceId"},
   			{"PatientSocialHistoryController","patientSocialHistoryId"},
   			{"PatientVisitClosureController","patientVisitClosureId"},
   			{"PatientVisitController","patientVisitControllerId"},
   			{"PharmacyController","pharmacyId"},
   			{"PrescriptionController","prescriptionId"},
   			{"PsychiatryConsultationController","psychiatryConsultationId"},
   			{"RateController","rateId"},
   			{"ReferralController","referralId"},
   			{"SuperBillController","superBillId"},
   			{"TemplateController","templateId"},
   			{"TranscriptionController","transcriptionId"},

   			{"TrialProspectController","ipAddress"},
   			{"UserController","sessionidId"},
   			{"MasterInsuranceCarrierController","CarrierMasterId"},
   			{"SpecialtyFormController","FormId"},
   			{"SpecialtyRecordController","RecordId"},
   			{"PatientVisitExamDaveController","PatientVisitExamId"},
   			{"PatientIntakeController","IntakeId"},
   			{"PatientReminderController","ReminderLogId"},
   			{"PatientEligibilityController","EligibilityResultId"},
   			{"ImportController","transcriptionId"},
   			{"ClinicController","clinicId"},
   			{"ClinicSiteController","SiteId"},
   			{"DocumentController","DocId"},
   			{"DocumentTypeController","DocTypeId"}
   		};

   		String entityId="";

   		for (int i=0;i<entityIdArr.length;i++)
   		{
   			if(entityIdArr[i][0].equals(controller))
   			{
   				entityId=entityIdArr[i][1];
   				break;
   			}
   		}
   		return entityId;
	}


	public static int convertHrsToMins(String str)
	{
		if(str.equals("") || str.indexOf(":")==-1 || (str.indexOf("AM")==-1 && str.indexOf("PM")==-1)) return 0;
		String ampm = "AM";
		if(str.indexOf("PM")!=-1) ampm="PM";

		int hr = Integer.parseInt(str.substring(0,str.indexOf(":")));
		int min = Integer.parseInt(str.substring(str.indexOf(":")+1,str.indexOf(" ")));
		int mint=0;

		if(ampm.equals("AM")){
		   if(hr==12) hr=0;
		   mint = hr*60 + min;
		}

		if(ampm.equals("PM")){
			if(hr!=12) hr = hr + 12;
		   mint = hr*60 + min;
		}

		return mint;
	}

	public static String longDateFormat(String date)
	{
		DateFormat df = DateFormat.getDateInstance(DateFormat.LONG);
		Date d=new Date(date);
		return (df.format(d));
	}

	public static boolean dateBefore(String dateStr)
	{
		// dateStr must be month/date/year
		try{
			Date dt1=new Date(dateStr);
			Date dt2=new Date("02/20/2007");
			return (dt1.before(dt2));			
		}catch(Exception e){
			return false;
		}
		

	}

	public static boolean checkAgeGt(String dob,int minAge) // function return true if age is greater than min age (21 yrs for growth Chart & 10 yrs for Immunizaton in patient summary)
	{
		String ageStr=getAge(dob);
		boolean ageGt=false;
		if(ageStr.indexOf("yrs")!=-1){
					int age=Integer.parseInt((ageStr.substring(0,ageStr.indexOf("yrs"))).trim());
					if(age>minAge) ageGt=true;
		}
		return ageGt;
	}

	// return current Date time according to zone
	public static String getCurrentDateTimeInTimeZone(HttpSession session)
	{
		String timeZone=(String)session.getValue("timeZone");
		String tz=timeZone.replace('(',' ').replace(')',' ');
		if(timeZone.equals("(GMT)")) timeZone="GMT";
		else if(timeZone.equals("(CST)")) timeZone="US/Central";
		else if(timeZone.equals("(MST)")) timeZone="US/Mountain";
		else if(timeZone.equals("(PST)")) timeZone="US/Pacific";
		else timeZone="US/Eastern";

		Calendar cDate =new GregorianCalendar(TimeZone.getTimeZone(timeZone));
		String currentDate=(cDate.get(Calendar.MONTH)+1)+"/"+cDate.get(Calendar.DAY_OF_MONTH)+"/"+cDate.get(Calendar.YEAR)+" &nbsp;&nbsp; "+((cDate.get(Calendar.HOUR)==0)?12:cDate.get(Calendar.HOUR))+":"+((cDate.get(Calendar.MINUTE)<10)?"0":"")+cDate.get(Calendar.MINUTE)+" "+ ((cDate.get(Calendar.AM_PM)==0)?"AM":"PM")+" "+tz;

		return currentDate;
	}

	public static String convertDateTimeInTimeZone(HttpSession session,String dateFromDb)
	{
		// dateFromDb format 2007-04-03 14:56:23.140 i.e., select getDate()

		String timeZone=(String)session.getValue("timeZone");
		String tz=timeZone.replace('(',' ').replace(')',' ');
		if(timeZone.equals("(GMT)")) timeZone="GMT";
		else if(timeZone.equals("(CST)")) timeZone="US/Central";
		else if(timeZone.equals("(MST)")) timeZone="US/Mountain";
		else if(timeZone.equals("(PST)")) timeZone="US/Pacific";
		else timeZone="US/Eastern";

		Timestamp ts = Timestamp.valueOf(dateFromDb);
		java.sql.Date dateA = new java.sql.Date(ts.getTime());

		Calendar calA = Calendar.getInstance();
		calA.setTimeZone(TimeZone.getTimeZone(timeZone));

		calA.setTimeInMillis(dateA.getTime());

		String dateTimeZone=(calA.get(Calendar.MONTH)+1)+"/"+calA.get(Calendar.DAY_OF_MONTH)+"/"+calA.get(Calendar.YEAR)+" &nbsp;&nbsp;"+((calA.get(Calendar.HOUR)==0)?12:calA.get(Calendar.HOUR))+":"+((calA.get(Calendar.MINUTE)<10)?"0":"")+calA.get(Calendar.MINUTE)+" "+ ((calA.get(Calendar.AM_PM)==0)?"AM":"PM")+" "+tz;

		return dateTimeZone;

	}





	/**
	*	returns Date Difference in days
	*	arg1 start date
	*	arg2 end date
	*	arg3 supplied date format (eg. MM-dd-yyyy HH:mm:ss)
	*
	**/

    public static int getDateDiff(String sdate1, String sdate2, String fmt)throws Exception{
        SimpleDateFormat df = new SimpleDateFormat(fmt);

        Date date1 = null;
        Date date2 = null;

        try{
            date1 = df.parse(sdate1);
            date2 = df.parse(sdate2);
        }catch (ParseException pe){
            pe.printStackTrace();
        }

        Calendar cal1 = null;
        Calendar cal2 = null;

        cal1=Calendar.getInstance();
        cal2=Calendar.getInstance();

        cal1.setTime(date1);
        long ldate1 = date1.getTime() + cal1.get(Calendar.ZONE_OFFSET) + cal1.get(Calendar.DST_OFFSET);

        cal2.setTime(date2);
        long ldate2 = date2.getTime() + cal2.get(Calendar.ZONE_OFFSET) + cal2.get(Calendar.DST_OFFSET);

        int hr1   = (int)(ldate1/3600000);
        int hr2   = (int)(ldate2/3600000);

        int days1 = (int)hr1/24;
        int days2 = (int)hr2/24;

        int dateDiff  = days2 - days1;

        return dateDiff;

    }


	public static String absoluteValue(double value)
	{
		String val="";
		NumberFormat numFormat = new DecimalFormat("0.00");
		if(value<0.00)
		{
				val="($"+numFormat.format(Math.abs(value))+")";
		}
		else
		{
				val="$"+numFormat.format(value);

		}
		return val;

	}

	//added on 030609 for HCFA template by Siddharth

	public static String retChkBoxImage(String value)
	{

		if(value.equalsIgnoreCase("X"))
			return "<img src=\"/html/images/cboxOn.gif\" alt=\"\" />";
		else
			return "<img src=\"/html/images/cboxOff.gif\" alt=\"\" />";
	}
	//end

	//added on 140709 for HCFA template by Siddharth
	//this function will convert yr no in (XXX) XXX - XXXX format
	public static String convertToUSPhoneFormat(String phoneNo)
	{

		StringBuffer sbPhone = new StringBuffer();
		if(phoneNo != null) phoneNo.trim();  //removing any extra spaces

		if(phoneNo != null && phoneNo.length() >= 12)
		{
			sbPhone = new StringBuffer("(" + phoneNo.substring(0,3) + ")" + " " + phoneNo.substring(4, 7) + "-" + phoneNo.substring(8));
			return sbPhone.toString();
		}
		else if(phoneNo == null || phoneNo.equalsIgnoreCase(" ") || phoneNo.equalsIgnoreCase(""))
		{
			sbPhone = new StringBuffer("(" + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + ")");
			return sbPhone.toString();

		}
		else
		{
			return phoneNo;
		}




	}//end on 140709 by siddharth
	
	//added by parth on 11212013
	public static String convertStringToUSPhoneFormat(String phoneNo)
	{

		StringBuffer sbPhone = new StringBuffer();
		if(phoneNo != null) phoneNo.trim();  //removing any extra spaces

		if(phoneNo != null && phoneNo.length() >= 12)
		{
			sbPhone = new StringBuffer( phoneNo.substring(0,3) + "-" + phoneNo.substring(4, 7) + "-" + phoneNo.substring(8));
			return sbPhone.toString();
		}
		else{
			return sbPhone.toString();
		}
		
	}
	
	//added on 072909 by siddharth
	public static String[] splitIntegerFractional(String num) {
		
		String[] numParts;
		if(num == null){
			numParts = new String[2];
			numParts[0] = "0";
			numParts[1] = "00";
			return numParts;
		}

		numParts = num.split("\\.");
		
		try{
			
			if(numParts.length == 1){
				int intPart = Integer.parseInt(numParts[0]);
				String temp = numParts[0];
				numParts = new String[2];
				numParts[0] = temp;
				numParts[1] = "00";
			}
			
		}catch(NumberFormatException ex){
			ex.printStackTrace(System.out);
			numParts = new String[2];
			numParts[0] = "0";
			numParts[1] = "00";
		}
		return numParts;

	}
	//end




	public static String  getGcodeValue(String gcode){

		if(gcode.equalsIgnoreCase("Controlled Drug")) return "4";

		for(int i=0; i< gcodeReasonDesc.length; i++)
			if(gcodeReasonDesc[i].equalsIgnoreCase(gcode)) return gcodeReasonValue[i];

		return "4";
	}


	public static String  getGcodeDesc(String gcode){

		if(gcode.equalsIgnoreCase("4")) return "Controlled Drug";

		for(int i=0; i< gcodeReasonValue.length; i++)
			if(gcodeReasonValue[i].equalsIgnoreCase(gcode)) return gcodeReasonDesc[i];

		return "";
	}

	public static String rPad (String strInput, int strlength)
	{

		int StartPoint=strInput.length();

		for (int i=StartPoint; i< strlength;i++)
		{
		strInput =strInput+" ";	
		}
	return strInput;
	}
	public static boolean IsNumeric (String numValue)
	{
		try
		{
			Integer.parseInt(numValue);
			return true;
		}
		catch(Exception e)
		{
		return false;	
		}
	}
	public static String NumberToDigit(String dNumber)
	{
		String Place[]=new String[7];
		Place[0]="  ";
		Place[1]=" Thousand ";
		Place[2]=" Million ";
		Place[3]=" Billion ";
		Place[4]=" Trillion ";
		Place[5]=" Quadrillion ";
		Place[6]=" Quintillion ";
		
		String sDigits="";
		String sFractions="";
		String TempDigit="";
		String sNumber="";
		int DecimalIndex=0;
		int Count=0;
	//  ' String representation of amount
		sNumber = dNumber.trim();
	//  ' Position of decimal place 0 if none
		DecimalIndex = 	sNumber.indexOf(".");
	 //   'Convert sFractions and set sNumber to dollar amount
	    if (DecimalIndex > 0)
	    {
	    //    ' Call Decimal Points function only two decimal places
	        sFractions = GetFractions(Double.parseDouble(sNumber.substring(DecimalIndex)));
	        sNumber =sNumber.substring(0,DecimalIndex).trim();
	    }
	    
	    Count = 0;
	    
	    while (!sNumber.equals(""))
	    {
	        if ((Count == 6) && (sNumber.length() > 3))
		    {
		        TempDigit = NumberToDigit(sNumber);
		        sNumber = "";
		    }
		    else
		    {	
		    	if (sNumber.length()<3)
		    		TempDigit = GetHundreds(sNumber);//  Right(sNumber, 3))
		    	else
		    		TempDigit = GetHundreds(sNumber.substring(sNumber.length()-3, sNumber.length()));//  Right(sNumber, 3))
		    }
		 
		    if (!TempDigit.equals(""))
		    	{
		    	sDigits = TempDigit + Place[Count] + sDigits;
		    	Count++;
		    	}
		    
		    if((sNumber.length()) > 3) 
		        sNumber =sNumber.substring(0, sNumber.length()-3); 
		    else
		        sNumber = "";
		    //Count = Count + 1;
	    }
	    if (!sFractions.equals(""))
	    {
	        if (!sDigits.equals(""))
	            sFractions = " And " + sFractions;
	        
	    }
	    return sDigits + sFractions;
	}

	/*
	'*******
	'$Function : GetHundreds
	'$Input : sNumber As String
	'$Output : String
	'$Description : ' Converts a number from 100-999 into text
	'***
	*/
	private static String GetHundreds(String sNumber)
	{
		
		String sResult="";
	    if (Integer.parseInt(sNumber)==0)
	    {
	    	return sResult;
	    }
	    
	    sNumber = ("000" + sNumber).substring(("000" + sNumber).length()-3); //Right("000" & sNumber, 3)
	    
	   // 'Convert the hundreds place
	    
	    if (!sNumber.substring(0, 1).equals("0"))
	         sResult = GetDigit(sNumber.substring(0, 1)) + " Hundred ";
	    
	    // 'Convert the tens and ones place
	    
	    if (!sNumber.substring(1, 2).equals("0"))
	        sResult = sResult + GetTens(sNumber.substring(1,3));
	    else
	        sResult = sResult + GetDigit(sNumber.substring(1,3));
	    
	    return  sResult;
	}
	
	/*'***
	'$Function : GetTens
	'$Input : TensText As String
	'$Output : String
	'$Description : Converts a number from 10 to 99 into text.
	'***
	*/
	private static String GetTens(String TensText) {
		
		String sResult=""; 
	    sResult = "";           //'null out the temporary function value
	    
	    if (Integer.parseInt(TensText.substring(0,1))== 1) //' If value between 10-19
	    {
	    	switch(Integer.parseInt(TensText))
	            {
	    		case 10: {sResult = "Ten";break;}
	            case 11: {sResult = "Eleven";break;}
	            case 12: {sResult = "Twelve";break;}
	            case 13: {sResult = "Thirteen";break;}
	            case 14: {sResult = "Fourteen";break;}
	            case 15: {sResult = "Fifteen";break;}
	            case 16: {sResult = "Sixteen";break;}
	            case 17: {sResult = "Seventeen";break;}
	            case 18: {sResult = "Eighteen";break;}
	            case 19: {sResult = "Nineteen";break;}
	            }
	    }
	    else                             //    ' If value between 20-99
	    	{
	    	
	    	switch(Integer.parseInt(TensText.substring(0,1)))
	    	  {
	            case 2: 
	            	{sResult = "Twenty ";
	            	 break;
	            	}
	            case 3: 
	            	{sResult = "Thirty ";
	            	break;
	            	}
	            case 4: 
	            	{sResult = "Forty ";
	            	break;
	            	}
	            case 5: 
	            	{sResult = "Fifty ";
	            	break;
	            	}
	            case 6: 
	            	{sResult = "Sixty ";
	            	break;
	            	}
	            case 7: 
	            	{sResult = "Seventy ";
	            	break;
	            	}
	            case 8: 
	            	{sResult = "Eighty ";
	            	break;
	            	}
	            case 9: 
	            	{sResult = "Ninety ";
	            	break;
	            	}
	    	  }
	    	sResult = sResult + GetDigit(TensText.substring(TensText.length()-1, TensText.length()));    //'Retrieve ones place
	    	}
	    
		 return sResult;
	 }

	/***
	'$Function : GetFractions
	'$Input : dFraction As Double
	'$Output : Double
	'$Description : Converts a number from 10 to 99 into text.
	'***/
	private static String GetFractions(Double dFraction){	    
		String sResult="";	    
	    sResult = "";
	    
	    if ((dFraction > 0) && (dFraction <= 0.2))
	    	return  "A Fifth ";
	    else if ((dFraction > 0.2) && (dFraction < 0.3)) 
	    	return  "A Quarter";
	    else if ((dFraction > 0.3) && (dFraction < 0.4))
	    	return  "A Third";
	    else if ((dFraction > 0.4) && (dFraction < 0.6))
	    	return  "A Half";
	    else if ((dFraction > 0.6) && (dFraction < 0.7))
	    	return  "A Two-Third";
	    else if ((dFraction > 0.7) && (dFraction < 1))
	    	return  "A Three-Fourth";
	    
	    return sResult;
	}
	

	/***
	'$Function : GetDigit
	'$Input : sDigit As String
	'$Output : String
	'$Description : Converts a number from 1 to 9 into text
	'***/
	
	private static String GetDigit(String sDigit) {
	    
		switch (Integer.parseInt(sDigit)){
			case 1: 
				return "One";
	        case 2: 
	        	return "Two";
	        case 3: 
	        	return  "Three";
	        case 4: 
	        	return  "Four";
	        case 5: 
	        	return  "Five";
	        case 6: 
	        	return  "Six";
	        case 7: 
	        	return  "Seven";
	        case 8: 
	        	return  "Eight";
	        case 9: 
	        	return  "Nine";
	        default : 
	        	return  "";
		}
	}
			
	public static String GetAge(String dtStart,String dtEnd)throws Exception 
	{
		String sReturn="";
		int ageDays=0,ageWeeks=0, ageMths=0, ageYrs=0;

		int ayear=0, amonth=0, adate=0;
		
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		
		Date dtEndDate = df.parse(dtEnd);
		ayear = dtEndDate.getYear();
		amonth = dtEndDate.getMonth();
		adate = dtEndDate.getDay();
		
		int byear=0, bmonth=0, bdate=0;
		
		Date dtStartDate = df.parse(dtStart);
		byear = dtStartDate.getYear();
		bmonth = dtStartDate.getMonth();
		bdate = dtStartDate.getDay();
		int lastDate=0;
		lastDate = getLastDayOfMonth(bmonth, byear);
		ageDays = adate - bdate;
		if (ageDays < 0) 
			{
			    ageDays = (lastDate - bdate) + adate;
			    amonth = amonth - 1;
			}

		ageMths = amonth - bmonth;
		if (ageMths < 0)
			{
			    ageMths = (12 - bmonth) + amonth;
			    ayear = ayear - 1;
			}
		ageYrs = ayear - byear;
		if (ageYrs < 0)
		    sReturn = "0 day";

		if (ageYrs == 0 && ageMths == 0)
	    {
			ageWeeks=(int) Math.round(((float)ageDays / 7)- 0.49);
			if(ageWeeks == 0)
				{
					if (ageDays == 1) 
						sReturn = "1 day";
					else
						sReturn = ageDays + " days";
				}
			else
			    {
			    	if (ageWeeks == 1) 
			            sReturn = "1 week";
			        else
			            sReturn = ageWeeks + " weeks";
			    }
	    }
		else
		{
		    String yrs="", mths="";
		    yrs = " yrs ";
		    mths = " mths";
		    if (ageYrs == 1) 
		    	yrs = " yr ";
		    if (ageMths == 1)
		    	mths = " mth ";
		
		    if(ageYrs == 0) 
		        sReturn = ageMths + mths;
		    else if ((ageYrs != 0) && (ageMths == 0))
		        sReturn = ageYrs + yrs;
		    else if (ageYrs >= 10) 
		        sReturn = ageYrs + yrs;
		    else
		        sReturn = ageYrs + yrs + ageMths + mths;
	    
		}
	return sReturn;
	}


	public static String  getDateToGMT(){
		
		String gmtDate = "";
        
		try {
        	  
			java.text.SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            java.util.Date date = format.parse(getCurrentDateInSQLForm());
            format.setTimeZone(TimeZone.getTimeZone("UTC"));
            format.applyPattern("yyyy-MM-dd HH:mm:ss z");
      		gmtDate = format.format(date).toString();
		}catch(Exception e){
			e.printStackTrace(System.out);
		}
		
		return gmtDate;
	}
	
	
	public static String getCurrentDateTime(String pattern)throws Exception {

		Calendar cal = Calendar.getInstance(TimeZone.getDefault());

		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		sdf.setTimeZone(TimeZone.getDefault());
		return  sdf.format(cal.getTime());

	}
	
	
	public static float Round(float Rval, int Rpl){
		  float p = (float)Math.pow(10,Rpl);
		  Rval = Rval * p;
		  float tmp = (float) Math.floor(Rval);
		  return (float)tmp/p;
	}
	
	public static double roundTwoDecimals(double d) {
	    DecimalFormat twoDForm = new DecimalFormat("#.##");
	    return Double.valueOf(twoDForm.format(d));
	}

	
	
	public static String getDatatabaseDate(String strDate){
			
		String fullDate = "";
	        
		try {	        	
			java.util.Date d=new java.util.Date(strDate);
			java.text.DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			fullDate = dateFormat.format(d);			    
		}catch(Exception e){
			e.printStackTrace(System.out);
		}
			
		return fullDate;
	}	

	public static String getSystemDateForPQRI() {
		Date d=new Date();
		return (d.getMonth()+1)+"-"+d.getDate()+"-"+(1900+d.getYear());
	}
	//added by Abhishek
	public static String checkStringReplacesSpecialChar(String stringToCheck)
	{
	   if(stringToCheck!=null) stringToCheck=stringToCheck.trim();
		if (stringToCheck==null || stringToCheck.equals(""))
		{
			return "" ;

		}
		else
		{
			StringBuffer stringBuffer = new StringBuffer();
			for(int i=0;i<stringToCheck.length();i++)
			{
				char c1 = stringToCheck.charAt(i);
				int c2 = (int)c1;
				switch(c2)
				{
					case '"':
						stringBuffer.append("&quot;");
						break;
					case '\'':
						stringBuffer.append("''");
						break;
					case '<':
						stringBuffer.append("&lt;");
						break;
					case '>':
						stringBuffer.append("&gt;");
						break;
				/*
					case '\n':
						stringBuffer.append("<BR>");
						break;
				*/


					case '\r':
						break;
					default:
						stringBuffer.append(c1);
						break;
				}
			}
			return stringBuffer.toString();
 		}
	}

//end
	
	public static String getHexValueOfImage(String ccImagePath) throws Exception {
		// TODO Auto-generated method stub
		String hexValue = "";
		try         
		{                 
			FileInputStream fis = new FileInputStream(new File(ccImagePath));   
			//BufferedWriter fos = new BufferedWriter(new FileWriter(new File(args[1])));                  
			byte[] bytes = new byte[800];                 
			int value = 0;
			do                 
			{                         
			
				value = fis.read(bytes);                         
				hexValue += toHexFromBytes(bytes).substring(0, value * 2);                  
			}while(value != -1);     
		}         
		catch(Exception e)         
		{                 
			e.printStackTrace();         
		} 
		return hexValue;
	}
	
	private static String toHexFromBytes(byte[] bytes) {
			if(bytes == null || bytes.length == 0)         
			{                 
				return ("");         
			}          
			
			// there are 2 hex digits per byte         
			
			StringBuilder hexBuffer = new StringBuilder(bytes.length * 2);          
			
			// for each byte, convert it to hex and append it to the buffer         
			
			for(int i = 0; i < bytes.length; i++)         
			{                 
			
				hexBuffer.append(toHexFromByte(bytes[i]));         
			
			}          
			
			return (hexBuffer.toString());   
	}

	private static String toHexFromByte(byte b) {
		
		final String[] hexSymbols = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };      
		final int BITS_PER_HEX_DIGIT = 4;
		byte leftSymbol = (byte)((b >>> BITS_PER_HEX_DIGIT) & 0x0f);         
		byte rightSymbol = (byte)(b & 0x0f);          
		
		return (hexSymbols[leftSymbol] + hexSymbols[rightSymbol]);
	}
	
	public static String removeMask(String str, String type){
		String modStr = "";
		String remStr = "-";
		try{
			//if(type.equals("SSN")){
				for(int i = 0; i < str.length(); i++ ) {
					if ((str.charAt(i)>='0' && str.charAt(i)<='9') || str.charAt(i)=='#' || str.charAt(i)==','){
						modStr += str.charAt(i);
					}
				}    
			//}
		}catch(Exception e){
			e.printStackTrace(System.out);
		}
		return modStr;
	}
	
	
	public static String addMask(String str, String type){
		String modStr = "";
		if(str.contains("-") && !str.contains("ext-")){
			return str;
		}
		try{
			if(type.equals("SSN")){
				for(int i = 0; i < str.length(); i++ ) {
					if (i==3 || i==5){
						modStr += "-";
					}
					modStr += str.charAt(i);
				}    
			}else if(type.equals("Phone")|| type.equals("Fax")){
				for(int i = 0; i < str.length(); i++ ) {
					if (i==3 || i==6){
						modStr += "-";
					}
					modStr += str.charAt(i);
				}   
			}else if(type.equals("Zip")){
				for(int i = 0; i < str.length(); i++ ) {
					if (i==5){
						modStr += "-";
					}
					modStr += str.charAt(i);
				}
			}
			//Added by Vijay on 09.01.2013
			else if(type.equals("TaxId"))
			{
				for(int i=0; i<str.length(); i++)
				{
					if(i==2)
					{
						modStr += "-";
					}
					modStr +=str.charAt(i);
				}
			}
			// ended here
		}catch(Exception e){
			e.printStackTrace(System.out);
		}
		return modStr;
	}

	public static void PopulateUSAreaCodeState(){
		try{
			DbResultset dbResultset = Query.executeSelect("select distinct St, State from USAreaCodeState order by State");
			if(dbResultset!=null && dbResultset.getRowCount()>0){
				state = new String[dbResultset.getRowCount()];
				stateAbbr = new String[dbResultset.getRowCount()];
				for(int i=0;i<dbResultset.getRowCount();i++){
					state[i] = dbResultset.get(i, "State");
					stateAbbr[i] = dbResultset.get(i, "St");
				}
			}
		}catch(Exception e){
			e.printStackTrace(System.out);
		}
	}
	
	public static String getDateWithZero(String dateStr) {
		int fSlash = dateStr.indexOf('/');
		int sSlash = dateStr.indexOf('/',fSlash+1);
		String month = dateStr.substring(0,fSlash);
		String date = dateStr.substring(fSlash+1,sSlash);
		String year = dateStr.substring(sSlash+1,dateStr.length());
		if(month.length()==1){ //5/01/2012
			month = "0"+month;	
		}
		if(date.length()==1){ //5/01/2012
			date = "0"+date;	
		}
		return month+"/"+date+"/"+year; 
	}
	

	public static String getAgeWithFullLabel(String dob) {
		int ageDays=0; int ageWeeks=0;int ageMths=0; int ageYrs=0;
		int k =dob.indexOf("-");
		if(k==-1) return "";
		String year  = dob.substring(0,4);
		String month = dob.substring(5,7);
		String days  = dob.substring(8,10);

		Date a=new java.util.Date();
		int ayear  = a.getYear()+1900;
		int amonth = a.getMonth()+1;
		int adate   = a.getDate();

		Date b=new java.util.Date(month+"/"+days+"/"+year);
		int byear  = b.getYear()+1900;
		int bmonth = b.getMonth()+1;
		int bdate   = b.getDate();

		int lastDate = getLastDayOfMonth(bmonth,byear);

		ageDays = adate - bdate;
		if(ageDays<0){
			ageDays = (lastDate - bdate) + adate;
			amonth--;
		}

		ageMths = amonth - bmonth;
		if(ageMths<0){
			ageMths = (12 - bmonth) + amonth;
			ayear--;
		}

		ageYrs = ayear - byear;
		if(ageYrs<0)
			return "0 day";

		if(ageYrs==0 && ageMths==0){
			ageWeeks= ageDays / 7;
			if(ageWeeks==0){
				if(ageDays==1) return "1 day";
					else return ageDays+" days";
				}else{
					if(ageWeeks==1) return "1 week";
					else return ageWeeks+" weeks";
				}
		}else{
			String yrs = " years ", mths = " months";
			if(ageYrs == 1) yrs = " year ";
			if(ageMths == 1) mths = " month ";

			if(ageYrs == 0) return ageMths + mths;
			if(ageYrs != 0 && ageMths == 0) return ageYrs + yrs;
			if(ageYrs >= 18) return ageYrs + yrs;

			return ageYrs + yrs + ageMths + mths;
		}
}
	
	
	public static String[] createCaseTypeList(DbResultset caseDBResult){ 
		String caseList[] = new String[caseDBResult.getRowCount()];      
		try{
			//System.out.println("3742::::");
			if(caseDBResult != null && caseDBResult.getRowCount() > 0){
				//System.out.println("3734::::");  
				for(int i=0;i<caseDBResult.getRowCount();i++){
					//System.out.println("3736::::");  
					caseList[i] = "\""+caseDBResult.get(i, "CaseTypeId")+"|"+caseDBResult.get(i, "CaseType")+"\"";              
				}  
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		//caseList[caseDBResult.getRowCount()] = null;
		//System.out.println("---------- 3470:::::"+caseList.length);        
		return caseList;
	}    

}

