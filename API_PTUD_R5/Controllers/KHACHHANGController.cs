using API_PTUD_R5.Model;
using API_PTUD_R5.Service;
using Microsoft.AspNetCore.Mvc;
using System.Collections.Generic;

namespace API_PTUD_R5.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class KHACHHANGController : ControllerBase
    {
        private readonly KHACHHANGService _bookService;

        public KHACHHANGController(KHACHHANGService bookService)
        {
            _bookService = bookService;
        }

        [HttpGet]
        public ActionResult<List<KHACHHANG>> Get()
        {
            System.Diagnostics.Debug.WriteLine("tring");
            return _bookService.Get();

        }


        [HttpGet("{id}")]
        public ActionResult<KHACHHANG> Get(long id)
        {
            var book = _bookService.Get(id);

            if (book == null)
            {
                return NotFound();
            }

            return book;
        }

        [HttpPost]
        public ActionResult<KHACHHANG> Create(KHACHHANG book)
        {
            KHACHHANG temp = _bookService.Create(book);
            if (temp != null)
                return Ok(book);
            return BadRequest();
        }

        [HttpPut("{id}")]
        public IActionResult Update(long id, KHACHHANG bookIn)
        {
            var book = _bookService.Get(id);

            if (book == null)
            {
                return NotFound();
            }

            _bookService.Update(id, bookIn);

            return Ok(bookIn);
        }

        [HttpDelete("{id}")]
        public IActionResult Delete(long id)
        {
            var book = _bookService.Get(id);

            if (book == null)
            {
                return NotFound();
            }

            _bookService.Remove(book.Id);

            return NoContent();
        }
    }
}
