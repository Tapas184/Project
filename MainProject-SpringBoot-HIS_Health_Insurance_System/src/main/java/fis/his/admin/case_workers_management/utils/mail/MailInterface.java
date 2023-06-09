package fis.his.admin.case_workers_management.utils.mail;

import fis.his.admin.case_workers_management.model.CwAndAdPojo;

public interface MailInterface {

	public boolean sendMail(CwAndAdPojo pojo) throws Exception;
	
	public boolean sendMailForRestPawword(CwAndAdPojo pojo) throws Exception;
}
