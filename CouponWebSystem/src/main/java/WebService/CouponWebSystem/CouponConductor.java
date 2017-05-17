package WebService.CouponWebSystem;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.xml.bind.annotation.XmlRootElement;

import core.Coupon;
import core.CouponType;
@XmlRootElement
public class CouponConductor  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private String title;
	private String startDate;
	private String endDate;
	private int amount;
	private String type;
	private String price;
	private String image;
	private String massage;
	
	
	public CouponConductor(){
		
	}
	
	public CouponConductor( String title, String startDate, String endDate, int amount, String type,
			String price, String image, String massage) {
		
		
		this.title = title;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.type = type;
		this.price = price;
		this.image = image;
		this.massage = massage;
	}
	
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getMassage() {
		return massage;
	}

	public void setMassage(String massage) {
		this.massage = massage;
	}

	public Coupon inject(){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd",Locale.US);

		SimpleDateFormat dateFormating = new SimpleDateFormat("dd/MM/yyyy");
		final String DATE_FORMAT_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
		SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_PATTERN, Locale.US);
		Coupon coupon = null;
		try {
			coupon = new Coupon(0, title, dateFormat.parse(startDate), dateFormat.parse(endDate), amount,
					CouponType.valueOf(type), massage, Double.parseDouble(price), image);
			System.out.println(dateFormat.parse(endDate));
			System.out.println(endDate);
		} catch (NumberFormatException | ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			
		
		return coupon;
		
	}


	@Override
	public String toString() {
		return "CouponConductor [id=" + id + ", title=" + title + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", amount=" + amount + ", type=" + type + ", price=" + price + ", image=" + image + ", massage="
				+ massage + "]";
	}

}
