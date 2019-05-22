package com.allinpay.qpay.demo.test;

import java.util.Map;
import java.util.TreeMap;

import com.allinpay.qpay.demo.common.QpayConstants;
import com.allinpay.qpay.demo.common.QpayUtil;

public class QuickPayTest {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		//agreeapply();// 协议申请
		//agreeconfirm();// 协议申请确认
		//payapply();//支付申请
		//pay();//支付申请确认
		//testQuery();//交易查询
	}
	
	public static void agreeapply() throws Exception{
		Map<String, String> params = buildBasicMap();
		params.put("meruserid","UID1527495655752");
		params.put("accttype","02");
		params.put("acctno","6259588982829844");
		params.put("idno","440982198809235377");
		params.put("acctname","聂宗波");
		params.put("mobile","18676296784");
		params.put("cvv2", "042");
		params.put("validdate", "0823");
		QpayUtil.dorequest(QpayConstants.SYB_APIURL_QPAY+"/agreeapply", params,QpayConstants.SYB_APPKEY);
	}
	
	public static void agreeconfirm() throws Exception{
		Map<String, String> params = buildBasicMap();
		params.put("meruserid","UID1527495655752");
		params.put("accttype","02");
		params.put("acctno","6259588982829844");
		params.put("idno","440982198809235377");
		params.put("acctname","聂宗波");
		params.put("mobile","18676296784");
		params.put("cvv2", "042");
		params.put("validdate", "0823");
		params.put("thpinfo", "{\"sign\":\"B909A69E0D6EC5DE8484B0751E807F89\",\"tphtrxcrtime\":\"\",\"tphtrxid\":0,\"trxflag\":\"\",\"trxsn\":\"sms18676296784\"}");
		params.put("smscode", "111111");
		QpayUtil.dorequest(QpayConstants.SYB_APIURL_QPAY+"/agreeconfirm", params,QpayConstants.SYB_APPKEY);
	}
	
	public static void payapply() throws Exception{
		Map<String, String> params = buildBasicMap();
		params.put("orderid", "QOD"+System.currentTimeMillis());
		params.put("amount", "1");
		params.put("subject", "标题");
		params.put("trxreserve", "快捷备注");
		params.put("notifyurl", "http://baidu.com");
		params.put("currency","CNY");
		params.put("validtime","30");
		params.put("agreeid","201805281801468072");//绑卡返回的agreeid
		params.put("memid", "201805281801467990");//绑卡返回的memid
		QpayUtil.dorequest(QpayConstants.SYB_APIURL_QPAY+"/payapplyagree", params,QpayConstants.SYB_APPKEY);
	}
	public static void pay() throws Exception{
		Map<String, String> params = buildBasicMap();
		params.put("orderid", "QOD1527501801781");//payapply的单号
		params.put("agreeid","201805281801468072");//绑卡返回的agreeid
		params.put("memid", "201805281801467990");//绑卡返回的memid
		params.put("smscode", "111111");
		params.put("thpinfo", "{\"sign\":\"\",\"tphtrxcrtime\":\"20180523112137\",\"tphtrxid\":0,\"trxflag\":\"trx\",\"trxsn\":\"11213799\"}");
		QpayUtil.dorequest(QpayConstants.SYB_APIURL_QPAY+"/payagreeconfirm", params,QpayConstants.SYB_APPKEY);
	}
	
	public static void testQuery() throws Exception{
		Map<String, String> params = buildBasicMap();
		params.put("trxid", "111879470000028860");
		Map<String, String> map = QpayUtil.dorequest(QpayConstants.SYB_APIURL_QPAY+"/query", params,QpayConstants.SYB_APPKEY);
		print(map);
	}
	
	public static Map<String, String> buildBasicMap(){
		TreeMap<String,String> params = new TreeMap<String,String>();
		params.put("appid", QpayConstants.SYB_APPID);
		params.put("cusid", QpayConstants.SYB_CUSID);
		params.put("version", "11");
		params.put("randomstr", System.currentTimeMillis()+"");
		return params;
	}
	
	public static void print(Map<String, String> map){
		System.out.println("返回数据如下:");
		if(map!=null){
			for(String key:map.keySet()){
				System.out.println(key+";"+map.get(key));
			}
		}
	}
	

}
