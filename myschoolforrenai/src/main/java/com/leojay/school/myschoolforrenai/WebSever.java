package com.leojay.school.myschoolforrenai;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.HashMap;

/**
 * Project:com.leojay.school.web
 * <p/>
 * Author:Crazy_LeoJay
 * Time:下午7:01
 */
public class WebSever {

    private Context context;

    private String studentId;
    private String password;
    private String loginCode;
    private int option;

    private Object term;
    private RWebMessage rWebMessage;
    private RLoginMessage rLoginMessage;

    private static String NAMESPACE;
    private static String METHODNAME;
    private static String URL;

    private static String XH;
    private static String PWD;
    private static String LOGINCODE;
    private static String OPTION;
    private static String XQ;

    private String rXH;
    private String rXM;
    private String rBJ;
    private String rNJ;
    private String rZY;
    private String rDEPCODE;

    private static String VERSION;
    private static int GRAD;
    private static int SCHEDULE;
    private static int EXAMINATION;
    private static int ATTENDANCE;

    /**
     * Instantiates a new Web sever.
     *
     * @param context the context
     */
    public WebSever(Context context) {
        this.context = context;
        XH = context.getResources().getString(R.string.input_xh);
        PWD = context.getResources().getString(R.string.input_pwd);
        LOGINCODE = context.getResources().getString(R.string.input_logincode);
        OPTION = context.getResources().getString(R.string.input_option);
        XQ = context.getResources().getString(R.string.input_xq);

        rXH = context.getResources().getString(R.string.return_xh);
        rXM = context.getResources().getString(R.string.return_xm);
        rBJ = context.getResources().getString(R.string.return_bj);
        rNJ = context.getResources().getString(R.string.return_nj);
        rZY = context.getResources().getString(R.string.return_zy);
        rDEPCODE = context.getResources().getString(R.string.return_depcode);

        VERSION = context.getResources().getString(R.string.version);
        GRAD = context.getResources().getInteger(R.integer.grad);
        SCHEDULE = context.getResources().getInteger(R.integer.schedule);
        EXAMINATION = context.getResources().getInteger(R.integer.examination);
        ATTENDANCE = context.getResources().getInteger(R.integer.attendance);

    }

    /**
     * Set arguments .
     * 使用 String 里的默认参数
     */
    public void setArguments() {
        NAMESPACE = context.getResources().getString(R.string.name_space);
        METHODNAME = context.getResources().getString(R.string.method_name);
        URL = context.getResources().getString(R.string.URL);
    }

    /**
     * Set arguments.
     * <p/>
     * if param = null 则使用默认
     *
     * @param nameSpace  the name space
     * @param methodName the method name
     * @param url        the url
     */
    public void setArguments(String nameSpace, String methodName, String url) {
        if (!nameSpace.equals(null)) {
            this.NAMESPACE = nameSpace;
        } else {
            NAMESPACE = context.getResources().getString(R.string.name_space);
        }
        if (!methodName.equals(null)) {
            this.METHODNAME = methodName;
        } else {
            METHODNAME = context.getResources().getString(R.string.method_name);
        }
        if (!url.equals(null)) {
            this.URL = url;
        } else {
            URL = context.getResources().getString(R.string.URL);
        }
    }

    private SoapObject RLoadArguments(String xh, String pwd, String loginCode, int option, Object xq) {

        SoapObject so = new SoapObject(NAMESPACE, METHODNAME);
        so.addProperty(XH, xh);
        so.addProperty(PWD, pwd);
        so.addProperty(LOGINCODE, loginCode);
        so.addProperty(OPTION, option);
        so.addProperty(XQ, xq);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.bodyOut = so;
        //这里如果设置为 false,那么在服务器端将获取不到参数值(如:将这些数据插入到数据库中的话)
        envelope.dotNet = true;
        //设置版本号
//        envelope.setOutputSoapObject(so);

        HttpTransportSE ht = new HttpTransportSE(URL);
        ht.debug = true;

        SoapObject soapObject = null;
        try {
            ht.call(NAMESPACE + METHODNAME, envelope);
            soapObject = (SoapObject) envelope.getResponse();
            Log.i("soapObject", soapObject + "");

        } catch (IOException | XmlPullParserException e) {
            e.printStackTrace();
        }
        return soapObject;

    }

    /**
     * The interface R web message.
     */
    public interface RWebMessage {
        /**
         * On arguments.
         *
         * @param object the object
         */
        void onArguments(SoapObject object);
    }

    public interface RLoginMessage {
        void onLoginState(boolean isLogin, HashMap<String, String> data);
    }

    /**
     * On r web message web sever.
     *
     * @param studentId   the student id
     * @param password    the password
     * @param loginCode   the login code
     * @param option      the option
     * @param term        the term
     * @param rWebMessage the r web message
     * @return the web sever
     */
    public WebSever onRWebMessage(String studentId, String password, String loginCode,
                                  int option, String term, RWebMessage rWebMessage) {
        this.studentId = studentId;
        this.password = password;
        this.loginCode = loginCode;
        this.option = option;
        this.term = term;
        this.rWebMessage = rWebMessage;
        return this;
    }

    /**
     * On r web message web sever.
     *
     * @param option      the option
     * @param term        the term
     * @param rWebMessage the r web message
     * @return the web sever
     */
    public WebSever onRWebMessage(int option, String term, RWebMessage rWebMessage) {
        this.studentId = getMessage().get(rXH);
        this.password = null;
        this.loginCode = getMessage().get(LOGINCODE);
        this.option = option;
        this.term = term;
        this.rWebMessage = rWebMessage;
        return this;
    }

    /**
     * On login web sever.
     *
     * @param studentId the student id
     * @param password  the password
     * @return the web sever
     */
    public void onLogin(final String studentId, final String password, final boolean isSavePassword,
                        final RLoginMessage rLoginMessage) {
        this.studentId = studentId;
        this.password = password;
        this.rLoginMessage = rLoginMessage;
        new Thread(new Runnable() {
            @Override
            public void run() {
                SoapObject soapObject = null;
                synchronized (this) {
                    soapObject = RLoadArguments(studentId, password, null, 0, VERSION);
                    boolean b = false;
                    try {
                        if (soapObject.toString().length() > 10) if (isSavePassword) {
                            String xm = soapObject.getPropertyAsString(rXM);
                            String bj = soapObject.getPropertyAsString(rBJ);
                            String nj = soapObject.getPropertyAsString(rNJ);
                            String zy = soapObject.getPropertyAsString(rZY);
                            String loginCode = soapObject.getPropertyAsString(LOGINCODE);
                            saveLoginCode(studentId, password, xm, bj, nj, zy, loginCode);
                            b = true;
                        } else {
                            b = false;
                        }
                    } catch (Throwable t) {
                        t.printStackTrace();
                    } finally {
                        rLoginMessage.onLoginState(b, getMessage());
                    }
                }
            }
        }).start();
    }

    /**
     * On version web sever.
     *
     * @param rWebMessage the r web message
     * @return the web sever
     */
    public void onVersion(RWebMessage rWebMessage) {
        onRWebMessage(0, VERSION, rWebMessage);
        startXF();
    }


    /**
     * On grad web sever.
     *
     * @param term        the term
     * @param rWebMessage the r web message
     * @return the web sever
     */
    public void onGrad(int term, RWebMessage rWebMessage) {
        onRWebMessage(GRAD, term + "", rWebMessage);
        startXF();
    }

    /**
     * On schedule web sever.
     *
     * @param rWebMessage the r web message
     * @return the web sever
     */
    public void onSchedule(RWebMessage rWebMessage) {
        onRWebMessage(SCHEDULE, null, rWebMessage);
        startXF();
    }

    /**
     * On examination web sever.
     *
     * @param rWebMessage the r web message
     * @return the web sever
     */
    public void onExamination(RWebMessage rWebMessage) {
        onRWebMessage(EXAMINATION, null, rWebMessage);
        startXF();
    }

    /**
     * On attendance web sever.
     *
     * @param rWebMessage the r web message
     * @return the web sever
     */
    public void onAttendance(RWebMessage rWebMessage) {
        onRWebMessage(ATTENDANCE, null, rWebMessage);
        startXF();
    }

    /**
     * Start xf.
     */
    public void startXF() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                SoapObject soapObject = null;
                synchronized (this) {
                    soapObject = RLoadArguments(studentId, password, loginCode, option, term);
                    rWebMessage.onArguments(soapObject);
                }
            }
        }).start();
    }

    private final static String SharedKEY = "SchoolInformation";

    private boolean saveLoginCode(String xh, String pwd, String name, String bj, String nj, String zy, String logincode) {
        SharedPreferences sharedpreferences = context.getSharedPreferences(SharedKEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        if (logincode != null) {
            try {
                editor.putString(rXH, xh);
                editor.putString(rXM, name);
                editor.putString(rBJ, bj);
                editor.putString(rNJ, nj);
                editor.putString(rZY, zy);

                editor.putString(PWD, pwd);
                editor.putString(LOGINCODE, logincode);
            } catch (Exception e) {
                Log.e("错误saveLoginCode", "save :::::" + e);
            }
        }
        return editor.commit();
    }

    public HashMap<String, String> getMessage() {
        HashMap<String, String> map = new HashMap<>();
        SharedPreferences sharedPreferences = context.getSharedPreferences(SharedKEY, Context.MODE_PRIVATE);
        String xh = sharedPreferences.getString(rXH, null);
        String xm = sharedPreferences.getString(rXM, null);
        String bj = sharedPreferences.getString(rBJ, null);
        String nj = sharedPreferences.getString(rNJ, null);
        String zy = sharedPreferences.getString(rZY, null);
        String mm = sharedPreferences.getString(PWD, null);
        String code = sharedPreferences.getString(LOGINCODE, null);
        map.put(XH, xh);
        map.put(PWD, mm);
        map.put(rXM, xm);
        map.put(rBJ, bj);
        map.put(rNJ, nj);
        map.put(rZY, zy);
        map.put(LOGINCODE, code);
        return map;
    }

//    private final static String XH = "xh";//<xh>6013202076</xh>
//    private final static String XM = "xm";//<xm>付嘉宇</xm>
//    private final static String BJ = "bj";//<bj>13机械设计2</bj>
//    private final static String NJ = "nj";// <nj>2013</nj>
//    private final static String ZY = "zy";//<zy>机械设计制造及其自动化</zy>
//    private final static String DEPCODE = "depcode";//<depcode>201</depcode>

}