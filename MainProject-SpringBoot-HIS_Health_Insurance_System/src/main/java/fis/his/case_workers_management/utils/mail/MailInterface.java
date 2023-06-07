package fis.his.case_workers_management.utils.mail;

import fis.his.case_workers_management.model.CwAndAdPojo;

public interface MailInterface {

	public boolean sendMail(CwAndAdPojo pojo) throws Exception;
}
