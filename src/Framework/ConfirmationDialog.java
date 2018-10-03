package Framework;

import org.openqa.selenium.By;

public class ConfirmationDialog {

	private static By dialogTitle = By.id("popup_title");
	private static By popupMsg = By.id("zys-popup-msg");
	private static By dialogYesBtn = By.id("popup_ok");
	private static By dialogNoBtn = By.id("popup_cancel");
	
	public ConfirmationDialog() {
		super();
	}


	/**
	 * @return the dialogTitle
	 */
	public static By getDialogTitle() {
		return dialogTitle;
	}

	/**
	 * @param dialogTitle the dialogTitle to set
	 */
	public static void setDialogTitle(By dialogTitle) {
		ConfirmationDialog.dialogTitle = dialogTitle;
	}


	/**
	 * @return the popupMsg
	 */
	public static By getPopupMsg() {
		return popupMsg;
	}



	/**
	 * @param popupMsg the popupMsg to set
	 */
	public static void setPopupMsg(By popupMsg) {
		ConfirmationDialog.popupMsg = popupMsg;
	}


	/**
	 * @return the dialogYesBtn
	 */
	public static By getDialogYesBtn() {
		return dialogYesBtn;
	}


	/**
	 * @param dialogYesBtn the dialogYesBtn to set
	 */
	public static void setDialogYesBtn(By dialogYesBtn) {
		ConfirmationDialog.dialogYesBtn = dialogYesBtn;
	}
}
