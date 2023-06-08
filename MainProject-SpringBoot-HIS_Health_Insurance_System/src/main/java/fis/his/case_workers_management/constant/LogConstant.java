package fis.his.case_workers_management.constant;

public   class LogConstant {
	
	private LogConstant() {

	}
	public static final String METHOD_EXECUTION_STARTED="<<Method execution started>>";
	public static final String METHOD_EXECUTION_ENDED="<<Method execution ended>>";
    public static final String MTHOD_DEBUG_STARTED="--Method debug started--";
	public static final String MTHOD_DEBUG_ENDED="--Method debug ended--";
    public static final String REDIRECT_GETALLCW_ADMIN_LIST="redirect:getalldetails";
	public static final String STATUS_ACTIVE="ACTIVE";
	public static final String STATUS_LOCKED="LOCKED";
	public static final String ACCOUNT_ACTIVE_EXCEPTION_MSG="Problems in account active";
	public static final String SET_STATUS_INACTIVE_SUCCESS_MSG="Successfullt account set inactive";
	public static final String ACCOUNT_ACTIVATED_MSG="Account activated";
	public static final String SET_STATUS_INACTIVE_ERROR_MSG="Problem in set account inactive";
	public static final String MAIL_SENT_SUCCESS_MSG="Mail sent to registed email, please visit the mail and unlock the account";
	public static final String MAIL_SENT_FAILD_MSG="Problem in sending mail";
	public static final String TEMPORARY_PASSWORD_LENGTH="temporarypassword.length";
	public static final String USER_NOT_FOUND="User not found";
	public static final String SES_MAILID="mailid";

}
