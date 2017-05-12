package tiendm.fb4j;

import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.Friend;
import facebook4j.Reading;
import facebook4j.ResponseList;
import facebook4j.conf.ConfigurationBuilder;
import facebook4j.internal.org.json.JSONException;

/**
 * Hello world!
 *
 */
public class App 
{
	private static final String PERMISSION = "ads_management, ads_read, create_event, create_note, email, "
			+ "export_stream, friends_about_me, friends_actions.books, friends_actions.music, "
			+ "friends_actions.news, friends_actions.video, friends_activities, friends_birthday, "
			+ "friends_education_history, friends_events, friends_games_activity, friends_groups, "
			+ "friends_hometown, friends_interests, friends_likes, friends_location, friends_notes, "
			+ "friends_online_presence, friends_photo_video_tags, friends_photos, friends_questions, "
			+ "friends_relationship_details, friends_relationships, friends_religion_politics, "
			+ "friends_status, friends_subscriptions, friends_videos, friends_website, friends_work_history, "
			+ "manage_friendlists, manage_notifications, manage_pages, photo_upload, publish_actions, "
			+ "publish_stream, read_friendlists, read_insights, read_mailbox, read_page_mailboxes, "
			+ "read_requests, read_stream, rsvp_event, share_item, sms, status_update, user_about_me, "
			+ "user_actions.books, user_actions.music, user_actions.news, user_actions.video, user_activities,"
			+ "user_birthday, user_education_history, user_events, user_friends, user_games_activity, "
			+ "user_groups, user_hometown, user_interests, user_likes, user_location, user_notes, "
			+ "user_online_presence, user_photo_video_tags, user_photos, user_questions, user_relationship_details, "
			+ "user_relationships, user_religion_politics, user_status, user_subscriptions, user_videos, "
			+ "user_website, user_work_history, video_upload, xmpp_login";
    public static void main( String[] args ) throws JSONException{
        System.out.println( "Hello World!" );
        try {
        	ConfigurationBuilder cb = new ConfigurationBuilder();
        	cb.setDebugEnabled(true)
        	  .setRestBaseURL("https://graph.facebook.com/v2.3/")
        	  .setOAuthAppId("2690")
        	  .setOAuthAppSecret("tiendm")
        	  .setOAuthAccessToken("EAACEdEose0cBAAjr0NUgl4V6gBYP8EnnvZCn44hAwob6UMBEpVwxEUZC0C7QczcKiU5SZBiSe057BxgUWt14RvUZAdnHjtZBuwL19hZBPavXLTd8UzZAj3yDX3W1ovhqaCC4b1sVssZAJiFCcXZB6HRQhSwldP7NpwAmB5PMZB1HeEAigXHM5rWJAKvZCpH50SftioZD")
        	  .setOAuthPermissions(PERMISSION);
        	FacebookFactory ff = new FacebookFactory(cb.build());
        	Facebook facebook = ff.getInstance();
	        ResponseList<Friend> friend = facebook.getFriends(new Reading().fields("id","name"));
	        System.out.println(friend);
		} catch (FacebookException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }
}
