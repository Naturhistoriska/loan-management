package se.nrm.dina.loan.web.util;

/**
 *
 * @author idali
 */
public enum CommonNames {

  NoResults,
  MissingCatNum,
  MissingFamily,
  MissingGenus,
  MissingSpecies,
  DuplicatValue,
  DestructivePolicy,
  ScientificLoanPolicy,
  CommonLoanPolicy,
  PreservationTypeNotSpecified,
  UploadFileFailed,
  Idle,
  IdleMsg,
  DataSourceConnectionError,
  RequestFailed,
  SendingEmailsFailed;

  public String getText() {
    return this.name();
  }
}
