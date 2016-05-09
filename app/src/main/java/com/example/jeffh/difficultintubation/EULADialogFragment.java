package com.example.jeffh.difficultintubation;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Created by jeffh on 2016-05-08.
 */
public class EULADialogFragment extends DialogFragment {

    private static String EULA =
            "Last updated: March 23, 2016\n" +
            "\n" +
            "Please read these Terms of Use (\"Terms\", \"Terms of Use\") carefully before using the Difficult Intubation Android application (the \"Service\").\n" +
            "\n" +
            "Your access to and use of the Service is conditioned on your acceptance of and compliance of these Terms. These Terms apply to all visitors, users, and others who access or use the Service.\n" +
            "\n" +
            "By accessing or using the Service you agree to be bound by these Terms. If you disagree with any part of the Terms, then you may not access the Service.\n" +
            "\n" +
            "Terms of Use:\n" +
            "\n"+
            "Before using the Service, you will be asked to indicate your acceptance of the terms and conditions of use as outlined below. By clicking a button marked \"I Accept\" \"I Agree\" \"Okay\" \"I Consent\" or other words and actions that similarly acknowledge your consent or acceptance of a Click Through Agreement, you accept the Terms of Use.\n" +
            "\n" +
            "You represent and warrant that you are at least 18 years of age and that you possess the legal right and ability to be legally bound by these Terms of Use. You agree to be responsible for your use of the Service and to comply with your responsibilities and obligations as stated in these Terms of Use. If you are not 18 years of age, you are not permitted to use this Service and must exit now.\n" +
            "\n" +
            "By using Service, you acknowledge that the Service is only for informational purposes only and is not a replacement for medical advice or medical decision making and the Service is not a substitute for proper communication with patients. Nothing contained on this Service or generated from this Service should be relied upon as a primary source for health or medical information.\n" +
            "\n" +
            "You agree to defend, indemnify, and hold the Service and the owners of the service harmless from any and all claims, liabilities, costs and expenses, including reasonable attorneys' fees, arising in any way from your use of the Service. In no event, including but not limited to negligence, shall the Service or the owners or employees of the Service be liable for any direct, indirect, special, incidental, consequential, exemplary or punitive damages arising from, or directly or indirectly related to, the use of, or the inability to use, the Service and the related functions.\n" +
            "\n" +
            "The information from the Service or generated from the Service should not be considered complete and should not be solely relied upon to suggest a course of treatment. The information generated from the Service is only meant to augment other sources of information and should not be considered a substitute for any or all personal responsibility and diligence. You acknowledge that the Service and the owners of the Service and affiliated entities are not responsible for any false, inaccurate, untrue, unauthorized, or incomplete information submitted by you or any user. The Service and the owners of the Service are not responsible for any errors or inaccuracies during the transfer of any data. The Service and the owners of the Service are also not responsible for any harm, loss, damage, injury, or death associated with any errors or inaccuracies during the transfer of any data.\n" +
            "\n" +
            "The Service and the owners of the Service shall not be responsible for any actual or perceived loss, damage, injury, or death associated with the use of the Service. The Service is not a replacement or substitute for standard medical care or standard medical decision making. The Service and the owners of the Service take no responsibility for your actions or the actions of any medical service providers.\n" +
            "\n" +
            "The Service and the owners of the Service does not store any data entered into the Service. The Service and the owners of the Service are not responsible for any storage of any data entered onto the Service. Any intentional or unintentional storage of data onto any electronic device is the sole responsibility of the user. The Service and the owners of the Service are not responsible for maintaining patient confidentiality or patient privacy; this is the sole responsibility of the user. Patient privacy and patient confidentiality is solely your responsibility and subject to all applicable local, state, provincial, national, and international laws and regulation.\n" +
            "\n" +
            "The Service may contain advertisements of third parties. The inclusion of advertisements on the Service does not imply endorsement of the advertised products or services by the Service or the owners of the Service. The Service and the owners of the Service shall not be responsible for any loss or damage of any kind incurred as a result of the presence of such advertisements on the Service. The Service is not responsible or Liable for any claims, statements, or conduct of any third party advertisers appearing on the Service's website. You shall be solely responsible for any correspondence or transactions you have with any third party advertisers. Your decision to access any links associated with third party advertisements on the Service is entirely at your own risk.\n" +
            "\n" +
            "The Service and its content is the property of its owners. The Service and its content are protected by federal and international copyright and trademark laws. The layout, presentation, an logo are trademarks and any unauthorized use of any trademarks, logos, or any other intellectual property belonging to the owners of the Service is strictly prohibited, and may be prosecuted to the fullest extent of the law.\n" +
            "\n" +
            "Termination\n" +
            "\n" +
            "We may terminate or suspend access to our Service immediately, without prior notice or liability, for any reason whatsoever.\n" +
            "\n" +
            "All provisions of the Terms which by their nature should survive termination shall survive termination, including, without limitation, ownership provisions, warranty disclaimers, indemnity and limitations of liability.";

    public interface EULADialogListener {
        public void onDialogPositiveClick(DialogFragment dialog);
        public void onDialogNegativeClick(DialogFragment dialog);
    }

    EULADialogListener eulaDialogListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            eulaDialogListener = (EULADialogListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement EulaDialogListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.terms_of_service_title)
                .setMessage(EULA)
                .setPositiveButton(R.string.accept_terms_of_service_button, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        eulaDialogListener.onDialogPositiveClick(EULADialogFragment.this);
                    }
                })
                .setNegativeButton(R.string.decline_terms_of_service_button, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        eulaDialogListener.onDialogNegativeClick(EULADialogFragment.this);
                    }
                });
        return builder.create();
    }
}
