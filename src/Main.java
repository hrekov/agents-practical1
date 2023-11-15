import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.StaleProxyException;
import jade.core.Runtime;

public class Main {
    private static void createBookSellerAgents(Runtime runtime) throws StaleProxyException {
        // Create bookseller agents
        var bookSellerProfile = new ProfileImpl();
        bookSellerProfile.setParameter(Profile.MAIN_HOST, "localhost");
        bookSellerProfile.setParameter(Profile.CONTAINER_NAME, "Book-Sellers-Container");
        var sellerContainer = runtime.createAgentContainer(bookSellerProfile);

        var sellerOne = sellerContainer.createNewAgent("Book Seller One", "BookSellerAgent", null);
        var sellerTwo = sellerContainer.createNewAgent("Book Seller Two", "BookSellerAgent", null);

        sellerOne.start();
        sellerTwo.start();
    }

    private static void createBookBuyerAgents(Runtime runtime) throws StaleProxyException {
        var bookBuyerProfile = new ProfileImpl();
        bookBuyerProfile.setParameter(Profile.MAIN_HOST, "localhost");
        bookBuyerProfile.setParameter(Profile.CONTAINER_NAME, "Book-Buyers-Container");
        var buyerContainer = runtime.createAgentContainer(bookBuyerProfile);

        buyerContainer
                .createNewAgent("", "BookBuyerAgent", new Object[]{"Programming Book"})
                .start();
    }

    public static void main(String[] args) throws StaleProxyException {
        var runtime = Runtime.instance();

        // Profile for main agent container
        var profile = new ProfileImpl();
        profile.setParameter(Profile.MAIN_HOST, "localhost");
        profile.setParameter(Profile.GUI, "true");
        runtime.createMainContainer(profile);

        createBookSellerAgents(runtime);
        createBookBuyerAgents(runtime);
    }
}
