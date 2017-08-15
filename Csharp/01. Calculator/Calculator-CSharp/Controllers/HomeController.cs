using System.Web.Mvc;

namespace Calculator_CSharp.Controllers
{
    public class HomeController : Controller
    {
        public ActionResult Index()
        {
            return View();
        }
    }
}