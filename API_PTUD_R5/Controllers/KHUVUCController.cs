using API_PTUD_R5.Model;
using API_PTUD_R5.Service;
using Microsoft.AspNetCore.Mvc;
using System.Collections.Generic;

namespace API_PTUD_R5.Controller
{
    [Route("api/khuvuc")]
    [ApiController]
    public class KHUVUCController : ControllerBase
    {
        private readonly KHUVUCService _bookService;

        public KHUVUCController(KHUVUCService bookService)
        {
            _bookService = bookService;
        }

        [HttpGet]
        public ActionResult<List<KHUVUC>> Get()
        {
            System.Diagnostics.Debug.WriteLine("tring");
            return _bookService.Get();
        }


        [HttpGet("{id}")]
        public ActionResult<KHUVUC> Get(long id)
        {
            var book = _bookService.Get(id);

            if (book == null)
            {
                return NotFound();
            }

            return book;
        }

        [HttpPost]
        public ActionResult<KHUVUC> Create(KHUVUC book)
        {
            KHUVUC temp = _bookService.Create(book);

            if (temp != null)
                return Ok(book);
            return BadRequest();
        }

        [HttpPut("{id}")]
        public IActionResult Update(long id, KHUVUC bookIn)
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
